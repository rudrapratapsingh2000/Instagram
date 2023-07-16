# Instagram 
Created a simple application using Spring Initializer With required depencdencies that manages Users of an instagram and also post of an  instagram i.e., An User can perform some operation such as sign-In, sign-Up, update,add Post and get All Post etc.

## Frame Work And Language
* Spring Boot 
* Java-17

# Data Flow 
 ## Model
   * ### User
        * #### userId
        * #### firstName
        * #### lastName
        * #### age
        * #### email
        * #### phoneNumber
   * ### Post
        * #### Id
        * #### createdDate
        * #### updatedDate
        * #### description
        * #### post
        * #### @ManyToOne -> User 
   * ### Authentication Token
        * #### tokenId
        * #### token
        * #### tokenCreationDate
        * #### @OneToOne -> User 
 * ## Dto
   * ### SignInInput 
     * #### email
     * #### password
   * ### SignUpOutput  
     * #### signUpStatus
     * #### signUpStatusMessage  
                
## Controller
  * ### User Controller
    * #### signInInstaUser
    * #### signUpInstaUser
    * #### updateUserDetails
  * ### Post Controller
    * #### createInstaPost
    * #### getPost
  
## Service 
  * ### Student Service
    * Sign In user
    * Sign up user 
    * createInstaPost
    * Update User
    * Get all Posts 
 * ### Post Service
   * Add post
 * ### Authentication Service
   * Save Token
   * authenticate 
## Data Structure
* String
* int
* long 

## Authors

- [@Rudra Pratap Singh](https://github.com/rudrapratapsingh2000)

