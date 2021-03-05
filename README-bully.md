# Bully

# Related Information

  * when the nodes in the system are initially turned on, they will automatically elect a coordinator and start 
    operating,just as if they were recovering from a failure.
    
# Assumptions

  * All nodes cooperate and use the same election algorithm.

  * The election algorithm at each node must make use of certain software facilities. These facilities include a local 
    operating system and a message handler. We assume that there are no "software bugs" in these facilities and that 
    they indeed offer the proper services.
    
  * If a node i receives a message M from node j,then that message M was sent by node j to node i at some earlier time.
    That is,we assume that the communication subsystem will not spontaneously generate messages.
    
  * All nodes have some (possibly limited) "safe" storage cells.

  * When a node fails (i.e., when its processor or its nonsafe memory fails), then node immediately halts all 
    processing.
    
  * There are no transmission errors.

  * All messages from node i to node j which do arrive at j are processed at j in the same order in which they were 
    sent.
    
  * The communication subsystem does not fail.

  * A node never pauses and always responds to incoming messages with no delay.


# References

  [1]. [Elections in a Distributed Computing System](http://vis.usal.es/rodrigo/documentos/papers/BullyAlgorithm.pdf)