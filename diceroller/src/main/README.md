# Dice Roller

The objective of this project is to create a program that inputs `number` and `type` of dice, rolls them, and sums them. Then we will extend this to drop an input number of low scores. This is a common task done in games such as *Dungeons & Dragons*. In doing this, you will use the following.

- functions
- `for` loops

## Example code

Take a look at the code you've been given. It does a common calculation from statistics called a *permutation* - the number of different ways you could choose $r$ objects from a group of $n$ objects, $r<n$, provided order matters.

Run the code and input relatively small numbers. For instance, if you choose 3 things from a group of 5, there are 20 ways to choose them. We write $_5 P_3$ and calculate it as $5!/3!$

Looking at the code, you've encountered most of it before. The new part is the method named `perm`, which is used as `perm(pop,sub)`. This is a function, just like $f(x) = x^2$ in a math class. The "formula" for it starts on line 8. Let's look at what it does.

```
    private static int perm(int p, int s) {
```

The keyword `private` says that this method is not accessible to other programs, in contrast to the `public` method `main`.

The keyword `static` is necessary for `perm` because we are calling it from `main`, which is also `static`. Any other motivation for using it is beyond us right now.

The keyword `int` declares that the method will produce an integer output. 

The name of the method is `perm` - this is just like the $f$ in a math function.

Inside the parentheses, we specify that the function will receive two variables. Inside the function, we'll call them `p` and `s`. When calling the function, we could give it literals: `perm(6,3)` will give 120. We could also pass it values held in variables, like `perm(pop, sub)`.

Methods or functions are the most fundamental thing in programming. When you have a task, it is a good habit to break it down into smaller tasks, each of which you assign to a method.

Once you have experimented with the sample code, feel free to delete the contents of `main` and the entirety of `perm`.

## Getting started

Start with a print line that informs the user of the purpose of the program - to roll a number of dice and add the results.

Taking a slightly different approach, we'll start with a repetition loop and then put things inside it. Import the `Scanner` class, then instantiate one with a name like `myScanner`. Print a greeting that asks the user whether they want to roll dice, prompting them for Y or N responses. Let's look at doing this succinctly, but in steps. Start with this.

```
    Scanner myScanner = new Scanner(System.in);
    String yesno = myScanner.next();

    System.out.println(yesno);
    myScanner.close();
```

Run this to make sure it works. Then try using `myScanner.next().toUpperCase()`. That should do the obvious, but make sure it works.

Now try using `myScanner.next().toUpperCase().charAt(0)`. This should pull the first character from a string (the first one is always numbered 0), but it should give you an error. This is because `charAt(0)` pulled out the first character and saved it as a `char`, not a string. It can be fixed by changing the type for `yesno` from `String` to `char` to let it know `yesno` will be just a single character. Make this change even if you aren't getting an error, as we'll assume it in the following.

Make a `while` loop with the condition `(yesno == 'Y')`. It will only run when the user has indicated (y)es, they want to roll some dice. It should look something like this.

```
    while (yesno == 'Y') {
        System.out.println(yesno);

        System.out.print("Do you want to roll some more dice? Y/N ");
        yesno = myScanner.next().toUpperCase().charAt(0);
    }
```

Weirdly, when I left that as `nextLine()`, I had some weird problem. I think it's related to something your text talks about in the section about using the `Scanner` to read integers. Run this and make sure it works. 

Experiment with commenting out the input line to see what happens. If you get stuck in an infinite loop, you can hit `ctrl-C` to break out. If you see why this happens without trying it, it's OK to skip the experiment!

## Inputting parameters

Now remove the line where you printed `yesno`. Replace it with a prompt to enter the `number` of dice the user wants to roll. Print this out to make sure it works. Test it.

Do the same thing to prompt the user for the number of `sides` for the dice - you may want to tell them it has to be more than 1 or you may assume the user will know. Print this out as well, and run the program to make sure it's working.

Now replace the two print statements with `System.out.println(number + "d" + sides);` - this will give results like 3d6 or 4d8. This is common notation for certain games: 3d6 means roll 3 dice with 6 sides. Run this and try a few inputs to see that it works.

## Dice rolling function

This is one of the most important ideas in programming, but it's basically stealing from math. If you remember having things like $f(x) = x^2$ in an algebra class, you know that $f(8)$ means to calculate $8 * 8$, which is 64. The input of this function was the 8, and the function returned the value of 64 as output. But in writing that function in Java, you'd have to do something like this.

```
public static double f(double x) {
    return x * x;
}
```

The `public` means it can be called by methods outside your program, the `static` allows you to call the method without creating an object of the class - like what we are doing by running `main`. The `double` before the `f` means that the answer will be a double precision number, and the `double` inside the parentheses means the input will be, too. There are certainly examples where the input and output are different things, but in math class, we were always working with real numbers - which more or less means double precision for computing.

Notice that `main` is of type `void`. That means there is nothing being returned. In older computer languages, such functions were called procedures or subroutines. When C++ became popular, we started calling all of them functions, and now Java calls them all methods. 

Also notice that `main` takes as input `String[] args`. So that's a variable named `args`, which can be any number of strings. Not important to us now, but it may be eventually!

As for our purposes, we need a function... err... method that accepts two integers as input and gives one as output. Place the following inside the `Main` class, but before the start of the `main` method.

```
        public static int roller(int num, int size) {
        return 0;
    }
```

Obviously, this function doesn't do what we want, it's just a placeholder. Get used to that trick! Make empty classes and methods at first to make sure they work, then fix their functionality. An engineer friend of mine told me that the key to programming is to ALWAYS start small and build up. This is called a *minimal working example*.

Now change the print statement in `main` to print the result from the function. It will be 0, but it will let you see that you got the function to work.

```
    System.out.println(number + "d" + sides + " = " + roller(number,sides));
```

Notice that the methods used by a class have to be inside that class unless they are being accessed using `import`. In other words, the method can't be in the section of the file wth the `import` statements. We will import common tools, but it will be a while before we start importing classes we build ourselves.

Now go back to `roller`. Notice that we named the input `num` and `size`. This is to emphasize that the variable name inside `main` is unimportant inside `roller`. The variables are "local" - meaning they can only be accessed inside their method. You have to pass them in through the arguments in parentheses and out by the `return` statement.

## Creating the rolls

Let's make this actually work. First, we need a randomizer, so make sure to import `Random`, then inside `roller`, instantiate it.

As we need to total our rolls, declare an integer named `sum` with a value of 0. For right now, just roll a single die and return the value. Your code should look like

```
    private static int roller(int num, int size) {
        Random rand = new Random();
        int sum = 0;

        sum += rand.nextInt(size) + 1;

        return sum;
    }
```

Test this to make sure it works!

## A good practice

I like to instantiate a single `Random` object outside the `roller` and `main` methods. This is because instantiating a new `Random` object every time you run the method seems like it may use a lot of system resources. Of course... my brain is set on old systems that measured memory in kilobytes, not gigabytes! However, in large scale projects, it could become important even now. Or, for that matter, an engineer might be designing a low-resource device like a space probe where they have to minimize storage.

To accomplish this, remove the line `Random rand = new Random();` and insert one right after the class declaration that says `private static final Random random = new Random();`. You probably won't see any difference in performance, but it's a good habit to be in.

## Using a new loop structure

It is certainly possible to do all the loops you need with `while`, but most programmers like the structure of a `for` loop. They look clunky, but they do a lot in very little code. The example code had a line like this.

```
    for (int index = s; index < p; index++) {
        <code>
    }
```
The `for` loop effectively contains three lines of code, separated by semicolons. The first declares and initializes the value of its counter, `index`, as `s`. The second returns `true` whenever `index < p`. The last tells the counter to increment by 1. For our purposes, let's start with this.

```
    private static int roller(int num, int size) {
        Random rand = new Random();
        int sum = 0;

        for(int i = 0; i < 10; i++) {
            sum += rand.nextInt(size) + 1;
        }

        return sum;
    }
```

This will start with `i = 0`, add a die roll to `sum`, then do it for `i = 1`, etc. until it does it for `i = 9`. But it will not execute with `i = 10`. Because it went 0 to 9, it rolled the die 10 times in total. Later, we will see that this ideal for a structure called *arrays*.

You probably got an alert that says `num` was never used in the method. Change the initial line to have this.

```
    for (int i = 0; i < num; i++)
```

Alternately, we could have had `i` count down from `num` to 1 - which is sometimes useful - but it accomplishes the same thing.

Now test this. I'd suggest repeatedly rolling 2d6 - which should give you results from 2 to 12, averaging 7. Test it enough that you feel convinced of this.

## Committing your repo

When you're done, save and commit, then turn the assignment in via Canvas.