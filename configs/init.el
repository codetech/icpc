;; -*- lexical-binding: t; -*-

;; Print this file out before a competition and transcribe it to
;; ~/.emacs.d/init.el when the competition begins.

;; To emulate a competition-like development environment, open emacs
;; like so:

;;   emacs -q -l /path/to/icpc/configs/init.el

(require 'cc-vars)
(require 'dired)
(require 'dired-x) ; dired-jump
(require 'elec-pair)
(require 'flymake)
(require 'gud)
(require 'ido)
(require 'paren)

;;; Keybindings

(defun ct-alternate-buffer ()
  "Alternate between the current buffer and the previous."
  (interactive)
  (switch-to-buffer (caar (window-prev-buffers))))

;; Use more ergonomic keybindings.

(cua-mode)

(global-set-key (kbd "C-a")   'mark-whole-buffer)
(global-set-key (kbd "C-o")   'find-file)
(global-set-key (kbd "C-b")   'switch-to-buffer)
(global-set-key (kbd "C-p")   'ct-alternate-buffer)
(global-set-key (kbd "C-s")   'save-buffer)
(global-set-key (kbd "C-M-r") 'revert-buffer)
(global-set-key (kbd "C-w")   'kill-this-buffer)
(global-set-key (kbd "C-/")   'dired-jump)
(global-set-key (kbd "C-f")   'isearch-forward)
(global-set-key (kbd "M-f")   'isearch-forward-regexp)
(global-set-key (kbd "C-r")   'query-replace)
(global-set-key (kbd "M-r")   'query-replace-regexp)
(global-set-key (kbd "C-0")   'delete-window)
(global-set-key (kbd "C-1")   'delete-other-windows)
(global-set-key (kbd "C-2")   'split-window-below)
(global-set-key (kbd "C-3")   'split-window-right)
(global-set-key (kbd "C-7")   'eval-last-sexp)
(global-set-key (kbd "C-8")   'eval-buffer)
(global-set-key (kbd "C-9")   'edebug-defun)

(define-key dired-mode-map   (kbd "C-o") 'find-file)
(define-key isearch-mode-map (kbd "C-v") 'isearch-yank-kill)
(define-key isearch-mode-map (kbd "C-f") 'isearch-repeat-forward)

(defun ct-ido-setup-hook ()
  (define-key ido-completion-map (kbd "C-o") 'ido-fallback-command))

(add-hook 'ido-setup-hook 'ct-ido-setup-hook)

;;; Appearance

(setq inhibit-splash-screen t)
(menu-bar-mode -1)
(tool-bar-mode -1)
(scroll-bar-mode -1)

(column-number-mode)

(show-paren-mode)
(setq show-paren-delay 0)

(load-theme 'adwaita)

;;; Formatting

(setq-default indent-tabs-mode nil)

;;; Features

(electric-pair-mode)
(global-subword-mode)
(add-hook 'emacs-lisp-mode-hook 'eldoc-mode)

;;; Overrides

(fset 'yes-or-no-p 'y-or-n-p)
(setq search-upper-case t
      kill-do-not-save-duplicates t
      confirm-nonexistent-file-or-buffer nil
      revert-without-query '(".*"))

;; Disable automatic backups in case the network is slow.
;; (setq backup-inhibited t
;;       setq auto-save-default nil)

;;; IDO

(setq ido-enable-flex-matching t
      ido-everywhere t
      ido-auto-merge-work-directories-length -1)
(ido-mode 1)

;;; Compilation

(defun ct-compilation-finish-function (buffer string)
  "Close the compilation buffer when successful."
  (when (and
         (string-match-p "compilation" (buffer-name buffer))
         (string-match-p "finished" string)
         (not
          (with-current-buffer buffer
            (search-forward "warning" nil t))))
    (run-with-timer 0.5 nil #'delete-window (get-buffer-window buffer t))))

(add-hook 'compilation-finish-functions 'ct-compilation-finish-function)

;;; Debugging

(defun ct-gud-mode-hook ()
  (local-set-key (kbd "<f5>") 'gud-step)
  (local-set-key (kbd "<f6>") 'gud-next)
  (local-set-key (kbd "<f7>") 'gud-finish))

(add-hook 'gud-mode-hook 'ct-gud-mode-hook)

(global-set-key (kbd "M-b") 'gud-break)
(global-set-key (kbd "M-n") 'gud-remove)

;;; Java

;; Perform automatic syntax checking with flymake.

;; The Makefile must include these lines:

;;   .PHONY: check-syntax
;;
;;   check-syntax:
;;           javac -Xlint $(CHK_SOURCES)

(defun ct-java-flymake-init ()
  "Initialize flymake arguments."
  (list "javac" (list (flymake-init-create-temp-buffer-copy
                       'flymake-create-temp-with-folder-structure))))

(add-to-list
 'flymake-allowed-file-name-masks
 '("\\.java\\'" ct-java-flymake-init flymake-simple-cleanup))

(defun ct-java-run ()
  "Run a program."
  (interactive)
  (shell-command "java Main < sample.in"))

(defun ct-java-run-diff ()
  "Test that a program outputs the correct result."
  (interactive)
  (shell-command "java Main < sample.in | diff sample.out -"))

(defun ct-java-debug ()
  "Start a Java debugger."
  (interactive)
  (let* (;; Ensure the debugger opens vertically.
         (split-height-threshold nil)
         (split-width-threshold 0)
         (buffer (get-buffer-create "*gud-Main*"))
         (window (display-buffer buffer)))
    (select-window window)
    (jdb "jdb Main sample.in")))

(defun ct-java-mode-hook ()
  ;; Use a smaller offset to potentially make room for the debugger.
  (setq c-basic-offset 2)
  (flymake-mode-on)
  ;; After using `M-x compile` once (probably to customize the
  ;; compilation command), recompile with the same settings as before.
  ;; The basic flow is to recompile, and if there are no errors, run
  ;; and diff.  If the program does not produce expected output,
  ;; debug.  Repeat until completion.
  (local-set-key (kbd "<f5>") 'recompile)
  (local-set-key (kbd "<f6>") 'ct-java-run)
  (local-set-key (kbd "<f7>") 'ct-java-run-diff)
  (local-set-key (kbd "<f8>") 'ct-java-debug))

(add-hook 'java-mode-hook 'ct-java-mode-hook)
