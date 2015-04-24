# Sequence Diagrams #

Version 1.0

## Sequence Diagram 1 ##

**Name:** Sign Up

**Actor:** Guest Users

**Preconditions:** None

**Action Steps:**
  1. Guest opens the web or android application and clicks on 'Log In' button.
  1. Guest chooses a unique username for the account.
  1. Guest provides a password and a valid email address for the account.
  1. Guest completes his/her registration and receives a confirmation mail.

**Post-conditions:**
  * The guest user is a _member_ (registered user) now.
  * He/she shall be able to log in to the system.

![http://i.imgur.com/gtEEaBS.png](http://i.imgur.com/gtEEaBS.png)

## Sequence Diagram 2 ##

**Name:** Log In (Sign In)

**Actor:** Members (Registered Users)

**Preconditions:** The user must have a member account.

**Action Steps:**
  1. Member opens the application, provides his/her username (or email address) and password.
  1. Member clicks on 'Log In' button.
  1. The system checks the member's information.

**Post-conditions:**
  * If the information is valid, the system shall enable the user into the system.
  * If the information is invalid, the system shall not let the user in and shall warn the user about the invalid information.

![http://i.imgur.com/j8LFsSK.png](http://i.imgur.com/j8LFsSK.png)

## Sequence Diagram 3 ##

**Name:** Forgot Password

**Actor:** Members (Registered Users)

**Preconditions:** User shall have an active account & must have forgotten his/her password.

**Action Steps:**
  1. User will click Forgot Password link.
  1. User will enter his/her email address in the forgot password window.
  1. User will enter the new password into the "New password" box.
  1. User will click "Change password" button.

**Post-conditions:**
  * Verification email is sent to the user.
  * User clicks the verification link.
  * System updates the password in the database.
  * User is notified that his/her password is successfully changed and he/she can use the system.

![http://i.imgur.com/fNUyGo2.png](http://i.imgur.com/fNUyGo2.png)

## Sequence Diagram 4 ##

**Name:** Search

**Actor:** Members and Guests (All Users)

**Preconditions:** None.

**Action Steps:**
  1. User opens the application. (Signing in is optional)
  1. User types a query for the content or the username and clicks the search button.
  1. The system returns a result set.

**Post-conditions:**
  * If the system finds any related results, it shall show the post to the user.
  * If there are far too many results to show at one time, the system shall sort the results based on some parameters (such as popularity, number of likes etc.) and group them into pages.
  * If there is no related result, the system shall tell the user that no such post is available.

![http://i.imgur.com/ohU90ay.png](http://i.imgur.com/ohU90ay.png)

## Sequence Diagram 5 ##

**Name:** Upload Post

**Actor:** Members

**Preconditions:** The user must have logged in.

**Action Steps:**
  1. Member finds the location or artifact he/she is interested in the application or clicks 'New Location or Artifact' button and adds name,type and other related information about the location or artifact.
  1. Member clicks on 'New Post' button.
  1. Member adds text/video/image related to selected cultural heritage to his/her post.
  1. Member selects/creates appropriate tags.
  1. Member clicks on 'Submit Post' button.

**Post-conditions:**
  * New content gets added to application and other users shall be able find and read it.
  * If new location or artifact haven't existed before post it becomes a valid location or artifact that can bu added upon by other members.
  * System shall connect post with related content using semantic tags and other category information in the post.
  * In the the process of creating new locations or artifacts system shall point out locations or artifacts that have too similar information so they can be merged.

![http://i.imgur.com/iiGK2qT.png](http://i.imgur.com/iiGK2qT.png)

## Sequence Diagram 6 ##

**Name:** View Post

**Actor:** Member or Guests

**Preconditions:** None(or Member for some actions)

**Action Steps:**
  1. User finds the location or artifact he/she is interested in the application.
  1. User reads posts about selected location or artifact.

**Post-conditions:**
  * Unregistered/Registered User is able to read the post
  * Member is able to vote the post
  * Member is able to report the post
  * Member is able to comment on the post
  * Member is able to like the post

![http://i.imgur.com/Z03uMNb.png](http://i.imgur.com/Z03uMNb.png)

## Sequence Diagram 7 ##

**Name:** Vote Post

**Actor:** Members (Registered Users)

**Preconditions:** User must be logged in to the system.

**Action Steps:**
  1. User opens the post he/she wants to vote on.
  1. User clicks the Upvote/Downvote button.
  1. Rating of the post gets updated by the system.

**Post-conditions:**
  * System shows the updated rating of the post.

![http://i.imgur.com/Icm8mTy.png](http://i.imgur.com/Icm8mTy.png)

## Sequence Diagram 8 ##

**Name:** Report Post

**Actor:** Members (Registered Users)

**Preconditions:** User must be logged in to the system.

**Action Steps:**
  1. User opens the post he/she wants to report.
  1. User clicks the "Report Post" button.
  1. System gets notified.

**Post-conditions:**
  * The poster user gets a mail from the system about the report.
  * Stories that has been reported more than a specific amount gets deleted.

![http://i.imgur.com/hQEXnTu.png](http://i.imgur.com/hQEXnTu.png)

## Sequence Diagram 9 ##

**Name:** Comment on Post

**Actor:** Members (Registered Users)

**Preconditions:** User must be logged in to the system.

**Action Steps:**
  1. User opens the post he/she wants to comment on.
  1. User enters the comment to the comment box that is under the post.
  1. User clicks the Send button.

**Post-conditions:**
  * The owner of the post gets a notification about the comment.
  * The comment is shown under the post.

![http://i.imgur.com/QwCFpaV.png](http://i.imgur.com/QwCFpaV.png)

## Sequence Diagram 10 ##

**Name:** Follow

**Actor:** Members (Registered Users)

**Preconditions:** User must be logged in to the system.

**Action Steps:**
  1. User opens the user page that he/she wants to follow.
  1. User clicks the follow button.

**Post-conditions:**
  * Followed user gets a notification which states who followed him/her.
  * Followed user is added to followed users list
  * Following user is added to following users list of followed person

![http://i.imgur.com/AMJAO6d.png](http://i.imgur.com/AMJAO6d.png)
## Sequence Diagram 11 ##

**Name:** Unfollow

**Actor:** Members (Registered Users)

**Preconditions:** User must be logged in to the system. User must be following the user who he/she wants to unfollow.

**Action Steps:**
  1. User opens the user page that he/she wants to unfollow.
  1. User clicks the unfollow button.

**Post-conditions:**
  * Unfollowed user is removed from followed users list.
  * Unfollowing user is removed from following users list of unfollowed person.

![http://i.imgur.com/LWe58P2.png](http://i.imgur.com/LWe58P2.png)

## Sequence Diagram 12 ##

**Name:** Tag Post

**Actor:** Members (Registered Users)

**Preconditions:** User must be logged in to the system.

**Action Steps:**
  1. User opens the post he/she wants to tag.
  1. User clicks the add tag button.
  1. User types the tag.
  1. User clicks the add button.
  1. Tag list of the post gets updated by the system.

**Post-conditions:**
  * System shows the updated tag list of the post.

![http://i.imgur.com/3J3MWdw.png](http://i.imgur.com/3J3MWdw.png)