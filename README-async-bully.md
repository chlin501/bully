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

  * ldrElected = ( ∀<sub>*i*</sub> : *status<sub>i</sub>* = Norm ^ *up<sub>ldr</sub>* )

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

  * `ID = { 1, 2, ... N }`

## Steps

  * Each node<sub>*i*</sub> initialize its status to **Norm**

  * If node<sub>*i*</sub> detects failure of its leader, it sets its *status* to **Elec1**

      * Stage 1 of organizing an election

          * Check if lesser(*i*) (id lesser than itself) are operational (status = Norm)

              * if **some** are operational, node *i* wait, giving higher priority nodes a chance to become leader

              * otherwise (none of lesser(*i*) nodes are operational), set its (node *i*) to Elec2 (of organizing an
                election)

                  * Stage 2 of organizing an election

                      * node *i* sends **Halt** message to node in great(*i*)

                      * a node that receives **Halt** message (in great(*i*))

                          * replies **Ack** message

                          * sets its *status* to **Wait**

                          * waits for the outcome of an election

                              * if it (in status Wait) detects failure of the node that sends Halt 
                                message,

                                  * starts the election itself (go to stage 1 of organizing an election)

# TODO

  * `zio-gRPC`

# References

  [1]. [1.4. Bully Algorithm](https://www.coursera.org/lecture/cloud-computing-2/1-4-bully-algorithm-K8QwJ)

  [2]. [Elections in a Distributed Computing System](http://vis.usal.es/rodrigo/documentos/papers/BullyAlgorithm.pdf)

  [2]. [Leader Election in Distributed Systems with Crash Failures](https://www3.cs.stonybrook.edu/~stoller/leader-election.html)
