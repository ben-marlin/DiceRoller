# Dice Roller

The objective of this project is to create a program that inputs `number` and `type` of dice, rolls them, and sums them. Then we will extend this to drop an input number of low scores. This is a common task done in games such as *Dungeons & Dragons*. In doing this, you will use the following.

- functions
- arrays
- `for` loops
- methods from the `Array` class

## Getting started

Taking a slightly different approach, we'll start with the repetition loop and then put things inside it. Import the `Scanner` class, then instantiate one with a name like `myScanner`. Print a greeting that asks the user whether they want to roll dice, prompting them for Y or N responses. Let's look at doing this succinctly, but in steps. Start with this.

```
    String yesno = myScanner.nextLine();

    System.out.println(yesno);
```

Run this to make sure it works, then try using `myScanner.next()` to see if there's any difference. You may want to change it back, I'll let you decide.

After you make sure this works, try using `myScanner.next().toUpperCase()`. That should do the obvious, but make sure it works.

Now try using `myScanner.next().toUpperCase().charAt(0)`. This should pull the first character from a string (the first one is always numbered 0), but it may give you an error. If so, this can be fixed by changing the type from `String` to `char` to let it know `yesno` will be just a single character. Make this change even if you aren't getting an error, as we'll assume it in the following.

Make a `while` loop with the condition `(yesno == 'Y')`. It will only run when the user has indicated (y)es, they want to roll some dice. In the body of the loop (i.e. between the braces), put something like this.

```
    System.out.println(yesno);

    System.out.print("Do you want to roll some more dice? Y/N ");
    yesno = myScanner.next().toUpperCase().charAt(0);
```

Weirdly, when I left that as `nextLine()`, I had some weird problem. I think it's related to something your text talks about in the section about using the `Scanner` to read integers. Run this and make sure it works. You might experiment with commenting out the input line to see what happens. If you get stuck in an infinite loop, you can hit `ctrl-C` to break out. If you see why this happens without trying it, it's OK to skip the experiment!

## Inputting parameters

Now remove the line where you printed `yesno`. Replace it with a prompt to enter the `number` of dice the user wants to roll. Print this out to make sure it works.

Do the same thing to prompt the user for the number of `sides` for the dice - you may want to tell them it has to be more than 1 or you may assume the user will know. Print this out as well, and run the program to make sure it's working.

Now replace the two print statements with `System.out.println(number + "d" + sides);` - this will give results like 3d6 or 4d8. This is common notation for certain games: 3d6 means roll 3 dice with 6 sides. Run this and try a few inputs to see that it works.

## Dice rolling function

This is one of the most important ideas in programming, but it's basically stealing from math. If you remember having things like $f(x) = x^2$ in an algebra class, you know that $f(8)$ means to calculate $8^2$, which is 64. The input of this function was the 8, and the function returned the value of 64 as output. But in writing that function in Java, you'd have to do something like this.

```
public static double f(double x) {
    return x * x;
}
```

The `public` means it can be called by methods outside your program, the `static` allows you to call the method without creating an object of the class - like what we are doing by running `main`. The `double` before the `f` means that the answer will be a double precision number, and the `double` inside the parentheses means the input will be, too. There are certainly examples where the input and output are different things, but in math class, we were always working with real numbers - which more or less means double precision for computing.

Notice that `main` is of type `void`. That means there is nothing being returned. In older computer languages, such functions were called procedures or subroutines. When C++ became popular, we started calling all of them functions, and now Java calls them all methods. 

Also notice that `main` takes as input `String[] args`. So that's a variable named args, which can be any number of strings. Not important to us now, but it may be eventually!

As for our purposes, we need a function... err... method that accepts two integers as input and gives one as output. Place the following inside the `Main` class, but before the start of the `main` method.

```
        public static int roller(int num, int size) {
        return 0;
    }
```

Obviously, this function doesn't do what we want, it's just a placeholder. Get used to that trick! Make empty classes and methods at first to make sure they work, then fix their functionality. An engineer friend of mine told me that the key to programming is to ALWAYS start small and build up.

Now change the print statement in `main` to print the result from the function. It will be 0, but it will let you see that you got the function to work.

Notice that the methods used by a class have to be inside that class unless they are being accessed using `import`. We will import common tools, but it will be a while before we start importing classes we build ourselves.

Now go back to `roller`. Notice that we named the input `num` and `size`. This is to emphasize that the variable name inside `main` is unimportant inside `roller` the variables are "local" - meaning they can only be accessed inside their method. You have to pass them in through the arguments in parentheses and out by the return statement.

## Creating the rolls

Let's make this actually work. First, above the return statement, declare an array of `num` integers. So if we are rolling 3d6, this will be 3 integers.

```
    int[] rolls = new int[num];
```

Run this to make sure it works. Don't try to print it, we just want to make sure we got that code typed correctly. In the case that `num` is passed 3, this has just reserved 3 spots in memory to store those 3 integers. By using `num` instead of 3, though, we will be able to roll any number of dice!

Now we'll add the dice rolls using a `for` loop. The same thing can be accomplished with a `while` loop, but the `for` loop is such a common construct, you should learn it early on. Add the following code after the declaration of `rolls`.

```
    Random rand = new Random();

    for (int index = 0; index < num; i++) {
        rolls[index] = rand.nextInt(1,size + 1);
        System.out.print(rolls[index] + "\t");
    }

    System.out.println("");
```

As usual, make sure this works before continuing. In general, you should NOT print inside a method, but this is just for the testing phase. Run this several times to make sure it's rolling the right number of dice and that the dice are giving numbers in the right range.

When that works, add `int sum = 0;` before the `for` loop. Then replace the print statement inside the loop with `sum += rolls[index];` - if you've spotted that we could do this without the array, please rest assured that we'll need the array in a later step! Remove the final print statement and change the `return` line to `return sum;`. Run this a few times to make sure it's giving you reasonable values. I would suggest rolling 1d6 over and over to see that you're getting the possible values.

## A good practice

I like to instantiate a single `Random` object outside the `roller` and `main` methods. This is because instantiating a new `Random` object every time you run the method seems like it may use a lot of system resources. Of course... my brain is set on old systems that measured memory in megabytes, not gigabytes! However, in large scale projects, it could become important even now.

To accomplish this, remove the line `Random rand = new Random();` and insert one right after the class declaration that says `private static final Random random = new Random();`. You probably won't see any difference in performance, but it's a good habit to be in.

## Dropping the low dice

As mentioned, there are game mechanics that allow you to roll multiple dice and drop the lowest. For example, Dungeons & Dragons has "4d6 drop the lowest" to generate scores from 3 to 18 - this practice skews the average higher. A newer mechanic has you roll 2d20 and take the highest (this is called "rolling with advantage"), and a new variant called DC20 has a mechanic where you roll 4d20 and take the highest - which skews the average MUCH higher. 

Sorry, I might be a nerd. And a mathematician.

For our purposes, we'll need to sort the dice rolls, then do our sum over just the higher ones. Let's start with just dropping one. First, rearrange `roller` to look like this.

```
    private static final Random rand = new Random();

    public static int roller(int num, int size) {
        int[] rolls = new int[num];

        for (int index = 0; index < num; index++) {
            rolls[index] = rand.nextInt(size) + 1;
        }

        int sum = 0;
        for (int index = 0; index < num; index++) {
            sum += rolls[index];
        }

        return sum;
    }
```

All this does is is separate the generation and summation steps. Test it to make sure your alteration still works!

Now, on the line after declaring `sum`, add a line that says `int drop = 1;` and change the second loop to `for (int index = drop; index < num; index++)`. This should drop the lowest 1. Run it for 1d6 - you should get 0. Try 2d6 a few times to see you get scores from 1 to 6, but more high numbers (that's hard to judge).

Once you're sure that works, delete the line that declares `drop` and change your signature line for the function to this.

```
    public static int roller(int num, int size, int drop) {
```

Run it now - it should give you an error. The problem is that you have declared a function `roller(num, size, drop)` but in `main`, you called a function `roller(number,sides)`. They're different shapes, because the declaration has 3 inputs and the call has 2. So change the call to `roller(number,sides,1)`. This should work the same as before. Test it to make sure.

Now return to `main` and add a line that asks the user how many low dice they would like to drop. Input as an integer named `droplow` and change the function call to `roller(number,sides,droplow)`. Test this using 1d6 drop 1 (should always get 0) and 4d6 drop 1 (should get 3 to 18, skewed to the higher end).

There is one problem with this... the code drops the first `droplow` dice... but those aren't necessarily the low rolls. To fix this, we need to sort our dice rolls. While sorting is a topic of and to itself, we're going to let a built-in feature do it for us.

Import the object `java.util.Arrays`. Then insert a line after you declared `sum` but before the summation loop starts. Insert `Arrays.sort(rolls);` - if you wanted to drop the highest, there is an option for this, but I'll leave that to your own research.

Run the code multiple times. Maybe roll 2d6 droppint the low 1 about 10 times. You should get an average around 4.5, whereas a just rolling 1d6 would average around 3.5. If your numbers are lower than 3.5, show me and we'll talk about it.

Once you have this working, review your code. Comment what each piece does in case you have to use it again later. Then save it, commit it, and submit it. Make sure you let me know on Canvas that you're done!