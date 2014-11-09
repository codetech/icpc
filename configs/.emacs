;; Print this file out before a competition and transcribe it to ~/.emacs when
;; the competition begins.

;; To emulate a competition-like development environment, open emacs like so:
;;
;;     emacs -q -l /path/to/icpc/configs/.emacs


;;;; Appearance

;; Don't show the screen with the emacs logo on it.
(setq inhibit-splash-screen t)

;; Hide window dressings.
(menu-bar-mode -1)
(tool-bar-mode -1)
(scroll-bar-mode -1)

;; Show the column number.
(setq column-number-mode t)

;; Match parenthesis.
(show-paren-mode t)
(setq-default show-paren-delay 0)


;;;; Behavior

;; Disable automatic backups in case the network is absurdly slow.
;; (setq backup-inhibited t)
;; (setq auto-save-default nil)

;; Delete selected text when you start typing.
(delete-selection-mode t)

;; Insert spaces instead of tabs.
(setq-default indent-tabs-mode nil)

;; Fill at the 80-character mark.
(setq-default fill-column 80)

;; Be camelcase-aware when moving and deleting words.
(global-subword-mode t)

;; Quickly browse through directories with C-x C-f.
(ido-mode t)

;; Enable C-x C-j for dired-jump.
(require 'dired-x)

;; Auto-indent with opening and closing braces.
(electric-indent-mode t)

;; Auto-close parenthesis, brackets, strings etc.
(electric-pair-mode t)

;; Allow for insertion of closing characters inside supposedly-closed areas.
(setq electric-pair-skip-self nil)


;;;; Keybindings

;; After using `M-x compile` once (probably to customize the compilation
;; command), press F5 to recompile with the same settings as before.
(global-set-key (kbd "<f5>") 'recompile)

;; Don't prompt to kill buffers (unless they are unsaved).
(global-set-key [(control x) (k)] 'kill-this-buffer)


;;;; Java

;;; Perform automatic syntax checking with flymake.

;; The Makefile must include these lines:
;; 
;;     .PHONY: check-syntax
;;
;;     check-syntax:
;;             javac -Xlint $(CHK_SOURCES)

(require 'flymake)
(add-hook 'java-mode-hook 'flymake-mode-on)

(defun codetech-java-flymake-init ()
  (list "javac" (list (flymake-init-create-temp-buffer-copy
                       'flymake-create-temp-with-folder-structure))))

(add-to-list 'flymake-allowed-file-name-masks
             '("\\.java$" codetech-java-flymake-init flymake-simple-cleanup))
