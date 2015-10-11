# International Collegiate Programming Contest Solutions

Contained within this repository are configurations for ICPC and solutions for ICPC problems.

Please create pull requests with your solutions.

Past problem sets are available at
[socalcontest.org](http://socalcontest.org/). We encourage you to solve past
problems for recreation and submit those solutions as well.


## Conventions

Each directory should have the following:

- A `sample.in` and `sample.out` file for testing correctness.
- A Makefile defining an `all` rule as the first (default) rule, which should
  compile the code.

For Java solutions, each directory should include:

- A `Main.java` file with a `public static void main` method.

A basic problem setup is available in the `template/` directory.


## Compiling

Define a Makefile like so:

```makefile
all:
	javac Main.java
```

(Possibly adding additional compilation targets, as in `javac Main.java MyClass.java`.)

Then run `make` in the directory containing the Makefile.

(Note that Makefiles must be indented with tabs, not spaces.)


## Testing

Simply run the following command to test compiled code:

```bash
java Main < sample.in | diff sample.out -
```

- No output: The program was succesful.
- Output: There were differences between your program's output and what was expected.


## Developing with Emacs

If you use the Emacs config at `configs/init.el` (by copying or symlinking it to
`~/.emacs.d/init.el`, for instance), then the following keyboard shortcuts will be provided:

- F5: Compile.
- F6: Run program, printing output.
- F7: Run program, diffing output against what is expected.

Line-by-line syntax / error checking will also be available.


## Developing with Vim

`cd` to the directory of the program you want to work on and go to town.

Alternate between Vim and your shell, issuing the commands from the "Compiling"
and "Testing" sections as needed. You can use `:shell` to temporarily leave Vim,
and when you're done you can type `exit` to return.


## Developing with Geany

Geany uses "regular" keyboard shortcuts, has tabs, a debugger, and regex
find-and-replace. It's also quite fast. If you don't like Emacs or Vim, and
Eclipse is too slow or complicated for you, then use this.

- Ctrl+Tab: Cycle through tabs.
- Ctrl+H: Find-and-replace.
- F2: Switch to editor.
- F4: Switch to terminal.
- Shift-F9: Compile.
- F5: Run program interactively.

Via "Build" > "Set Build Commands" > "Execute commands", you can set "1." (F5)
to something more convenient (like the diffing command from the "Testing"
section), and set "2." to an additional menu-item-only command.
