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

# Protocol

  * Each ch node in the system is assigned at system creation time a unique identification number
    
      * Id is used as priority 
      
      * The highest id will become the coordinator
    
      * Id is between 1 ~ N where N (such that N is natural number)

  * A node initializes the selection (in a scenario where the failure node won't be recovered)

      * Part 1
    
          A. A node (*i*) contacts all nodes (*j*) whose ids are higher than its own (i < j < n such that n is the 
            number of nodes in the system)
            
              * If any of nodes (*j*) responds 
            
                  * the node (*i*) gives up the chance to become the coordinator and wait for the announcement of higher 
                    priority nodes (*j*) 
        
                  * but after some time no responce from some nodes (*j*), the node (*i*) restarts procedure from A
                
              * Otherwise (all higher priority nodes do NOT respond after T seconds)
    
                  * all ndoes (*j*) fail
    
      * Part 2
    
          * The node *i* sends `Halt` message to lower priority node *k*
    
              * Then the node *k* stop processing, and set its status to `Election`
    
          * Once all nodes are in a known states
    
              * The node *i* sends out "I am elected" message to the lower priority nodes 
    
                  * and sets its status to "Reorganization"
    
                  * and sets its coordinator to *i* (itself) 
    
              * The node *k* receives "I am elected" message, 
    
                  * the node *k* check if the `Halt` message is sent from node *i*
     
                  * the node *k* set its coordinator to *i*
     
                  * the node *k* set its status to "Reorganization"

# References

  [1]. [Elections in a Distributed Computing System](http://vis.usal.es/rodrigo/documentos/papers/BullyAlgorithm.pdf)