# Maze solver

## Intro

The goal of this is not that you come up with code for a solution as quickly as
possible, but that we get an idea of what you think is nice code. Feel free to
use comments to explain why you're doing things a certain way, especially if
it's a choice you're making for good reasons such as "don't (yet) know the
idiomatic Clojure way of doing this" or "to save time".

This assignment is adapted from one that exists somewhere on the internet, don't
peek.

## Description

This is a tiny maze solver.

A maze is represented by a matrix:

```clojure
[[:S 0 1]
 [1  0 1]
 [1  0 :E]]
```

- _S_ : start of the maze
- _E_ : end of the maze
- _1_ : This is a wall that you cannot pass through
- _0_ : A free space that you can move through.

The goal is the get to the end of the maze. You cannot move diagonally.

A solved maze will have a _:x_ in the start, the path, and the end of the maze,
like this:

```clojure
[[:x :x 1]
 [1  :x 1]
 [1  :x :x]]
```

This is how your solver should return its solution.


## Instructions

0. Make sure you have [Leiningen](https://github.com/technomancy/leiningen)
   installed. And a JVM.


1. Solve the mazes provided in the tests. Run the tests with `lein test` or
   `lein test-refresh` (which will automatically reload and re-run the tests
   whenever you save a change).

   Note that for these tiny mazes there is only one possible route, so there is
   no need to consider if it's the shortest.


### Optional extensions

It's tough to know how long it takes to get up to speed with Clojure, so in case
there's time: here are some additional steps. The benefit of doing these is that
more code gives us a better idea of your skills.


2. Expand the solver to find the shortest route, using a simple
   exhaustive search. You'll need to make up some slightly larger mazes as test
   inputs.

3. (even more optional) Implement a "proper" pathfinding algorithm of your
   choice to find the shortest route. This could be a classic like `A*` but
   anything that is smarter than the previous step is fine.

