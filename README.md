# Asynchronous Bully Algorithm

  * Failure detector
  
# Specification

## Leader Election for Synchronous Systems

### Safety

  * Never disagree on the leader

  * *ldr*: its leader
 
  * *status*

      * Normal: node is in the normal mode of operation, the *ldr* is significant
    
      * Others: a new leader is being elected
    
  * SLE1

      * Node(id = *i* , status = Normal) and Node(id = *j*, status = Normal); then *ldr<sub>i</sub>* = *ldr<sub>j</sub>*

### Liveness

  * the system eventually enter a state in which the leader is operational and all operational nodes have status Norm
    
  * ldrElected = ( ∀*<sub>i</sub>* : *status<sub>i</sub>* = Norm ^ *up<sub>ldr</sub>* )

  * SLE2

      * there exists a constant c such that if no failures or recoveries occur for a period of at least c , then by the end of that period, the system reaches a state satisfying *ldrElected*.
        
##

  * The algorithm organizes the system into disjoint groups such that all members of a group agree on the group's leader.

# The Bully Algorithm for Synchronous Systems

## Properties for System

  * A **fixed** set of nodes

  * A communication network

  * Node may crash and recover (No other type of failure)

  * Node has access to a stable storage

  * Node communicates by sending messages

  * Node communication is FIFO

# TODO

  * `zio-gRPC`

# References

  [1]. [Leader Election in Distributed Systems with Crash Failures](https://www3.cs.stonybrook.edu/~stoller/leader-election.html)
