// 3 => 1
struct A1 {
  char a;
};

// 4 => 2
struct A1 {
  char a;
  char b;
};

// 5 => 3
struct A1 {
  char a;
  char b;
  char c;
};

// 6 => 4
struct A1 {
  char a;
  char b;
  char c;
  char d;
};

// 7 => 5
struct A1 {
  char a;
  char b;
  char c;
  char d;
  char e;
};

// 8 => 6
struct A1 {
  char a;
  char b;
  char c;
  char d;
  char e;
  char f;
};

// 9 => 7
struct A1 {
  char a;
  char b;
  char c;
  char d;
  char e;
  char f;
  char g;
};

// 10 => 9
struct A1 {
  char a;
  char b;
  char c;
};
struct A2 {
  A1 a;
  A1 b;
  A1 c;
};

// 11 => 12
struct A1 {
  char a;
  char b;
  char c;
  char b;
};
struct A2 {
  A1 a;
  A1 b;
  A1 c;
};

// 12 => 16
struct A1 {
  char a;
  char b;
  char c;
  char b;
};
struct A2 {
  A1 a;
  A1 b;
  A1 c;
  A1 d;
};

// 13 => 20
struct A1 {
  char a;
  char b;
  char c;
  char d;
  char e;
};
struct A2 {
  A1 a;
  A1 b;
  A1 c;
  A1 d;
};

// 14 => 25
struct A1 {
  char a;
  char b;
  char c;
  char d;
  char e;
};
struct A2 {
  A1 a;
  A1 b;
  A1 c;
  A1 d;
  A1 e;
};

// 15 => 30
struct A1 {
  char a;
  char b;
  char c;
  char d;
  char e;
  char f;
};
struct A2 {
  A1 a;
  A1 b;
  A1 c;
  A1 d;
  A1 e;
};

// 15 => 27
struct A1 {
  char a;
  char b;
  char c;
};
struct A2 {
  A1 a;
  A1 b;
  A1 c;
};
struct A3 {
  A2 a;
  A2 b;
  A2 c;
};

// 16 => 36
struct A1 {
  char a;
  char b;
  char c;
  char d;
  char e;
  char f;
};
struct A2 {
  A1 a;
  A1 b;
  A1 c;
  A1 d;
  A1 e;
  A1 f;
};

// 16 => 36
struct A1 {
  char a;
  char b;
  char c;
  char d;
};
struct A2 {
  A1 a;
  A1 b;
  A1 c;
};
struct A3 {
  A2 a;
  A2 b;
  A2 c;
};

// 17 => 48
struct A1 {
  char a;
  char b;
  char c;
  char d;
};
struct A2 {
  A1 a;
  A1 b;
  A1 c;
  A1 d;
};
struct A3 {
  A2 a;
  A2 b;
  A2 c;
};

// 18 => 64
struct A1 {
  char a;
  char b;
  char c;
  char d;
};
struct A2 {
  A1 a;
  A1 b;
  A1 c;
  A1 d;
};
struct A3 {
  A2 a;
  A2 b;
  A2 c;
  A2 d;
};
