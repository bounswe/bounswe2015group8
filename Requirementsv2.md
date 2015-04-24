# Requirement Analysis v2 #

## Summary ##

Our project is based on the idea of reviving the cultural heritage which is getting lost. It lets users interact each other. Naturally, the same area will have diverse stories changing after some time and distinctive angles from distinctive perspectives relying upon every individual. At the point when glance at any memory, the map will demonstrate its location.

We are interested in how people are related those cultural tools (pictures, food, music) and where they came from. We want people to get knowledge from each other by their memories. Furthermore, the system suggestion brings users who are interested in the same history together. To do so, we use tagging. Users can like, dislike, vote and comment their sharings.

Individuals will have the capacity to peruse and impart their stories from this time and area with the help of pictures, features and sound recordings.These stories can also be semantically searched by location, rating, categories and so on. Also, we recommend users new stories based on their search parameters such as time, their age, where they live, and what they’ve liked before.

Furthermore, there will be a report system for the inappropriate stories and fake accounts/pins.

## Background ##

Our project aims to create a platform for people to share their memories and their thoughts about cultural heritage. We want our users to revive the cultural heritage that are getting lost as time goes by.

The aim of this document is to explain the requirements of our 'Cultural Heritage Project' with glossary and explanations of requirements. In Glossary subsection, users can see what terms mean specifically. Requirements subsection lists the features that our application has to implement. It is mostly the technical part of the project. Requirements section is also divided into subsections to give a better idea and be systematic. It has sign up / log in part to list requirements for guests, and user requirements to specify the authority of the users.

## Glossary ##
  * **System**: - The software that we are developing.
  * **User**: A person or a piece of code(i.e. web crawlers) that 'uses' this system.
  * **Using this system**: Reading or grabbing the content provided by this system. Adding new content to the system. Commenting or voting in the system.
  * **Guest**: A user that is not registered to the system.
  * **Member**: A user that is registered to the system.
  * **Register (Sign up)**: Providing an e-mail or a username and, a password and in return being accepted by the system as a member. Members have privilage interfere with the content on the system.
  * **Log in (Sign in)**: Entering the e-mail or the username and, the password previously provided in order to act as a member.
  * **Content**: Text, audio or video related to cultural heritage.
  * **Cultural heritage**: Built environment, natural environment, artifacts.
  * **Built environment**: Buildings, townscapes, archaeological remains, etc.
  * **Natural environment**: Rural landscapes, agricultural heritage, coasts and shorelines, etc.
  * **Artefacts**: Books, documents, objects, pictures, songs, etc.
  * **Cultural Heritage Object**: A content that is defined within the boundaries of cultural heritage, such as Turkish Delight, Mona Lisa, Giotto's Campanile, Anastasian Wall, etc.
  * **Owner**: The member that provided that specific post.
  * **Comment**: Expessing an opinion related to a post under that post.
  * **Like/Dislike (Vote)**: Expressing a positive/negative feedback about a post or a comment.
  * **Semantic tagging**: [Semantic Tagging and Searching](SemanticTaggingAndSearching.md)
  * **Graphical User Interface**: An interface provided to users which enables interaction with the system.
  * **Unauthorized access**: Trying to log in via using false credentials.
  * **Post**: A combination of text, images and videos about a given Cultural Heritage Object that a user wants to share that collectively form a story.
  * **Inappropirate story**: A story which is irrelevant, false or misleading.
  * **Internet browsers**: A software application for retrieving, presenting and traversing information resources on the World Wide Web.
  * **Web Server**: An information technology that distributes information on the World Wide Web.
  * **Recover from failure**: Since any web content is served from a web server, a recovery from a failure is making the server work correctly.
  * **Back-up**: Having a copy of the content that is stored in the web server.
  * **Requirements**: Things that has to done in order to make sure that this system works.

## Requirements ##

> ### **1. Functional Requirements** ###
> > #### **1.1. Sign Up/Log in Requirements** ####
      * 1.1.1 - Guest users shall be able to sign up to the system with a username and a password they choose, after providing an email account.
      * 1.1.2 - Users with an account shall log in to the system by providing their usernames/emails and their passwords.
      * 1.1.3 - In the case of forgetting their passwords or usernames, users shall be able to receive this information via their emails.
> > #### **1.2. User Requirements** ####
> > > ##### **1.2.1 Guest users** #####
        * 1.2.1.1 - Guest users shall be able to view the posts.
        * 1.2.1.2 - Guest users shall not be able to upload or like posts.
        * 1.2.1.3 - Guest users shall be able to view cultural heritage objects.
        * 1.2.1.4 - Guest users shall not be able to edit or create new cultural heritage objects.
        * 1.2.1.5 - Guest users shall be able to register using a unique id, a password and an email address. Upon registering, they become members.
> > > ##### **1.2.2. Members** #####
        * 1.2.2.1 - Members shall be able to upload, view and like posts.
        * 1.2.2.2 - Members shall be able to edit their posts.
        * 1.2.2.3 - Members shall be able to report posts as spam or inappropriate content.
        * 1.2.2.4 - Members shall be able to comment on their and other people's posts.
        * 1.2.2.5 - Members shall be able to create new cultural heritage objects unless it exists.
        * 1.2.2.6 - Members shall be able to add fields to cultural heritage objects, the acceptable fields being its creator, its place and its date.
        * 1.2.2.7 - Members shall be able to add semantic tags to cultural heritage objects.

> > #### **1.3. Post Requirements** ####
      * 1.3.1 - Posts shall consist of texts/videos/images or combinations of those.
      * 1.3.2 - Each post shall only have one owner.
      * 1.3.3 - Each post shall be editable and removable by the owner.
      * 1.3.4 - Each post shall contain at least one tag.
      * 1.3.5 - Each post shall be able to be linked to at least one cultural heritage object.
      * 1.3.6 - Tags of the posts shall be categorized regarding the location, culture, time, language etc.
      * 1.3.7 - Users shall be able to post location tags by using Google Maps interface.
      * 1.3.8 - Users shall be able to add non-location tags by text.
      * 1.3.9 - Users shall be able to rate posts 1-5 and like them.
> > #### **1.4. Search Requirements** ####
      * 1.4.1 - The system shall provide a search mechanism for searching among the posts and cultural heritage objects.
      * 1.4.2 - The search mechanism shall include search by the tags of the post, username of the post owner, and fields of cultural heritage objects.
      * 1.4.3 - The search mechanism shall make use of _semantic search_ by allowing user to enter semantic tags.
      * 1.4.4 - The search results shall return cultural heritage objects and posts.
      * 1.4.5 - When the user searches a query which does not return any results, the system shall give some related results by using semantic search and recommendations.
> > #### **1.5. Subscription Requirements** ####
      * 1.5.1 - The system shall provide a subscription system.
      * 1.5.2 - Members shall be able to follow/unfollow other members.
> > #### **1.6. Recommendation Requirements** ####
      * 1.6.1 - The system shall provide a recommendation system.
      * 1.6.2 - Users shall be recommended upon liking or rating posts, and upon searching something that does not return any results.
      * 1.6.3 - Recommendations will be based on tags.
      * 1.6.4 - Recommendations upon liking or rating posts shall return posts of the same cultural heritage object that have similar tags. If there are no similar posts, posts of other cultural heritage objects that have similar tags will be returned.
      * 1.6.5 - Recommendations upon searching something that does not return any results will provide the user the correct version of the input if the input has a typo, and will return entries in the same semantic domain (if specified) that has the maximum amount of common tags to the given input.
> > #### **1.7. Cultural Heritage Object Requirements** ####
      * 1.7.1 - Cultural heritage objects shall be unique.
      * 1.7.2 - Cultural heritage objects shall be semantically tagged by the user.
      * 1.7.3 - Cultural heritage objects shall contain fields creator, date and place.
      * 1.7.4 - Cultural heritage object fields shall be optional and provide support for more than one input.
      * 1.7.5 - Cultural heritage objects shall be linked at least zero posts.
> > ##### **1.8. Semantic Tags Requirements** #####
      * 1.8.1 - Members shall be able to add semantic tags to the system.
      * 1.8.2 - Members shall be guided to create well defined and mutually exclusive semantic tags.



> ### **2. Non-Functional Requirements** ###
> > #### **2.1. Supportability and Maintainability** ####
      * 2.1.1 - The system shall be updated according to the new technologies and have its bugs fixed regularly.
      * 2.1.2 -  The system shall be able to deal with additional international conventions such as languages, or number formats, styles.
      * 2.1.3 - The system shall support the use of multiple browsers such as Safari, Google Chrome And Mozilla Firefox and maybe some newer versions of Internet Explorer.
> > #### **2.2. Usability** ####
      * 2.2.1 - The user manual shall be well structured.
      * 2.2.2 - There shall be informative error messages (when necessary)
      * 2.2.3 - The graphical user interface shall not be complicated and well-formed.
> > #### **2.3. Reliability** ####
      * 2.3.1 - The system shall be available for service when requested by end-users and shall have an availability of 99%.
      * 2.3.2 - The system shall have a failure rate as low as possible.
> > #### **2.4. Effectiveness** ####
      * 2.4.1 - Minimum average response time of system and application shall be obtained.
      * 2.4.2 - The system shall be capable of recovering itself quickly.
> > #### **2.5. Scalability** ####
      * 2.5.1 - The system of the program shall be capable of increasing total throughput under an increased load when resources are added.
> > #### **2.6. Security** ####
      * 2.6.1 - Unauthorized access to the system and its data is not allowed
      * 2.6.2 - All program data must be backed up in a convenient period of time and the backup copies stored in a secure location which is not in the same location of storage as the system.

