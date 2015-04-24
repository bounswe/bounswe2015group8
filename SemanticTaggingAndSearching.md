### Research Group ###

  * [Berk Salmanoğlu](BerkSalmanoglu.md)
  * [Gökcan Çantalı](GokcanCantali.md)
  * [Göktuğ Erce Gürel](GoktugErceGurel.md)
  * [Sinan Harputluoğlu](SinanHarputluoglu.md)

# Details #

## What is Semantic Tagging? ##
![https://bhc3.files.wordpress.com/2008/05/dave-winer-political-terms-semantic-tag.png](https://bhc3.files.wordpress.com/2008/05/dave-winer-political-terms-semantic-tag.png)

**Semantic tagging** is a technology that makes it easier to find and organize information. It, in the various ways that it is understood, is a term that describes many of findability approaches, i.e. Approaches which intent to make information easier to find. Also, it is about creating efficiency and making things work better Semantic tagging is by no means an accepted concept with an agreed upon definition. Other than the obvious "tagging for meaning", semantic tagging means different things to people coming from different parts of the information management field. It may be used interchangeably with "semantic indexing" in contexts where "indexing" is used for "tagging".

Some people would argue that semantic tagging is nothing new. It can be defined as the assigning of selected controlled vocabulary (aka taxonomy) terms, especially by trained indexers, to content items, such as articles, images, or other documents, to reflect the meaning of the content. Nevertheless, there are publishers that consider semantic tagging to be something more than mere controlled vocabulary-based human indexing. Semantic Tagging is different from traditional subject indexing of documents, because it focuses on concepts of content instead of documents as a whole. It can be used in any level like articles, sections, paragraphs, tables, figures, equations, sidebars, videos etc.

A popular website that is based on tagging is [Flickr](https://www.flickr.com/), which is a photo management and sharing application. [Instagram](https://instagram.com/) is also another popular application which allows users to tag their photos whick makes it easier for other users to find photos.

## How Should be the Tags? Some Problems and Solutions ##

In order to be computer-interpretable, tags have to be unique, standardized, and their names have to be agreed upon. Moreover, the set of tags has to be huge. Is there a source of such tags? Fortunately, there is. The tremendous job of creating definitions of a huge number of terms has already been done and the result can be found in the ‘central knowledge source of mankind’ — Wikipedia.

Besides displaying articles in a standardized (semi-structured) way, Wikipedia also has a standardized way of uniquely naming titles and the URLs of associated articles which have been created and are constantly perfected by thousands of contributors.

Polysemy is solved in Wikipedia by adding additional information directly into names. ‘Orange (colour)’ and ‘Orange (fruit)’ both have a clear meaning now. In addition, take an often used example of a word with plenty of meanings — ‘java’. The article ‘Java’ represents an island in Indonesia, ‘Java (programming language)’ is a popular programming language, ‘Java coffee’ is a coffee produced on the island of the same name, ‘Java (cigarette)’ refers to a brand of Russian cigarettes, etc. Note that an article with the name consisting solely of the term in question (in this case - ‘Java’) tends to be the most representative one, if there is any.

Synonymy, on the other hand, is solved in Wikipedia by the system of redirects. Try to type ‘http://en.wikipedia.org/wiki/Movie’ into the address bar. You will be automatically redirected to the article named ‘Film’.

Thanks to DBpedia, it is possible to use the information from Wikipedia in an elegant way. DBpedia is a project that extracts structured information from Wikipedia and makes it accessible on the Web in the form of RDF triples, under the GNU Free Documentation License. As Wikipedia contains articles about many general-purpose concepts, DBpedia can also be seen as a huge ontology that assigns URIs to a large number of concepts. This knowledge base can serve as the universal controlled vocabulary we are looking for.

Put into context, tags referring to DBpedia are not just words anymore, they act as objects with properties specified further by literals or typed links to other objects. In DBpedia there are some properties common to all tags, such as: an abstract, a picture, labels in multiple languages, and several properties dealing with classification.

For example, if we look at the DBpedia page for ‘Keith Richards’, we can learn some additional properties about him such as his year of birth, his type of voice, the genre of music he plays, as well as his connections to other tags, such as that he is: born in ‘Dartford’, a current member of the band ‘The Rolling Stones’, plays ‘Fender Telecaster’ and ‘Gibson Les Paul’, and has occupations of ‘Music producer’, ‘Musician’, and ‘Songwriter’.

## What is Semantic Search? ##
![http://hlwiki.slais.ubc.ca/images/thumb/4/4c/Approaches_to_semantic_search.png/400px-Approaches_to_semantic_search.png](http://hlwiki.slais.ubc.ca/images/thumb/4/4c/Approaches_to_semantic_search.png/400px-Approaches_to_semantic_search.png)

_Search_ is a way to discover information, access services, increase our store of knowledge. **Semantic Search** is defined as search for information based on the intent of the searcher and contextual meaning of the search terms, instead of depending on the dictionary meaning of the individual words in the search query. It uses a number of techniques which includes using keyword to concept mapping, graph pattern recognition and entity extraction. By this way, it provides more meaningful results and finds the most relevant results in a website or database. Semantic search also uses location, synonyms of a term, current trends, word variations and other natural language elements as part of the search.

Let us give a real world example of Semantic Search. Suppose you are working on your computer and someone is asking you "Do you have windows there?". You can understand that the person wants to know whether you have Microsoft Windows operating system on your computer. But if you are approaching a Realtor looking for office space and ask him the same question, it takes a completely different meaning. In that context, the question means whether the office space you are discussing has any windows and ventilation in the room.

Humans can understand the question based on the context and give relevant answer. But how about search engines? Can they differentiate the question based on the context? While most of them are not capable of understanding the context, there are some advanced search engines like _Google_ and _Bing_ are able to do that to some extent.

## How do Search Engines Use Semantic Search? ##

If a search engine provides the users with results based on the intent and contextual meaning of the query terms, we can say that it makes use of semantic search. However, a smart search engine does not only consider the intent and the context, but also some other factors which are essential to give the most relevant results. Some of these factors are the following:
  * Current trend
  * Location of search
  * Variations of words in Semantic Search
  * Synonyms
  * Concept matching
  * Natural language queries
  * Change of meaning based on the group of words

# Resources #
  * http://www.techulator.com/resources/5933-What-Semantic-Search.aspx
  * http://www.hedden-information.com/SemanticTagging.pdf
  * http://www.techopedia.com/definition/23731/semantic-search
  * http://www.w3.org/2001/sw/sweo/public/UseCases/Faviki/