# Welcome Friend

This repo is meant to be used in conjunction with my talk on Kotlin... I'll add details on that later 
It is an implementation of Game of Life - I hope you find it useful.
There are multiple branches:
- [Master](https://github.com/rmaclean/game-of-life-in-kotlin): This is for me the nicest implementation of GOL in Kotlin. It isn't what I do in the talk though.
- [More traditional style](https://github.com/rmaclean/game-of-life-in-kotlin/tree/more-traditional-style): This is a more traditional way of using loops and if statements over lambdas. I wouldn't say one is OO and the other is functional at all, they just different styles.
- [demoMode](https://github.com/rmaclean/game-of-life-in-kotlin/tree/demoMode): This is the start state for the demo, simple and empty
- [talkEndState](https://github.com/rmaclean/game-of-life-in-kotlin/tree/talkEndState): This is the end of the talk. You'll notice some overlap from master but there is weirdness, like some functions being in the Point class, some outside and some as extensions so I can talk to them. It doesn't have the cool Random stuff either.

---

# Running this

`gradle run` should do it 😊

---