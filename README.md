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


## Compiling

Define a Makefile like so:

```makefile
all:
	javac Main.java
```

(Possibly adding additional compilation targets, as in `javac Main.java MyClass.java`.)

Then run `make -k` in the directory containing the Makefile.


## Testing

Simply run the following command to test compiled code:

```bash
java Main < sample.in | diff sample.out -
```

If there is no output, the program was succesful. Output indicates differences
between your output and what is expected.


## Developing with Emacs

If you use the emacs config at `configs/.emacs` (by copying or symlinking it to
`~/.emacs`), then the following conveniences will be provided:

- F5: Compile.
- F6: Run program, printing output.
- F7: Run program, diffing output against what is expected.