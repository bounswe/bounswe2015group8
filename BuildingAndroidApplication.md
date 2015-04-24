<table border='0'> <tbody> <tr><td width='30%' valign='top'><h1>What is Android?</h1>
Android is a mobile OS, based on the Linux kernel and currently developed by Google. It was primarily designed for touchscreen mobile devices but it is now widely used among many devices such as home automation systems, game consoles, digital cameras, televisions, wrist watches and even cars. Its touch input originates from real-world actions like swiping, tapping, pinching and, a virtual keyboard. However Android OS also supports other types of inputs like keyboards, mice, gaming console controllers and, various types of sensors.<pre></pre>As of 2013, Android is the most widely used mobile operating system and the highest-selling operating system overall. As of July 2013, the Google Play Store has had over one million Android applications published and over 50 billion applications downloaded.<pre></pre>Google shares Android's source code with public under open source licenses. However most of Android devices ship with a combination of open source and proprietary software.<pre></pre>Android is a favorable option for tech companies because of its low-cost and highly customizable OS for high-tech devices. Its open source nature is also a big plus which also attracts individual developers along with companies.<pre></pre><h2>What is ROM?</h2>'ROM' stands for 'Read Only Memory'. However in this context, it refers to the operating system software that is stored in the 'Read Only Memory' of the device. All Android devices come with a stock ROM installed by the manufacturer. But, if you root your device, you gain the ability to install custom ROMs that will completely change the look and feel of the software. <pre></pre><b>Rooting</b> is basically gaining admin priviliges on your device. Android's open-source flavor allows developers to take stock ROMs and modify them. This modification involves stripping out all the bloatware that are installed by manufacturers, optimizing the software, unlocking some of the built-in features, and making an even better version of the Android software. Developers who make these custom ROMs then release the software to the general public. To obtain these custom firmware, one should <b>flash</b> the ROM to his/her device. <pre></pre>The most popular website for Android customization is <a href='http://www.forum.xda-developers.com'>xda-developers</a>. The most popular open-source firmware for Android devices are <a href='http://www.cyanogenmod.org/'>Cyanogenmod</a> and <a href='https://source.android.com/'>Android Open Source Project</a>.</td><td width='20%' valign='top'>
<b>Android's architecture diagram</b><pre></pre>
<img src='http://upload.wikimedia.org/wikipedia/commons/thumb/a/af/Android-System-Architecture.svg/592px-Android-System-Architecture.svg.png' /></td></tr></tbody> </table>
<br>
<table border='0'> <tbody> <tr> <td width='20%' valign='top'><img src='http://developer.android.com/design/media/building_blocks_landing.png' /></td> <td width='30%' valign='top'><h1>Building Your First App</h1> Before building android apps you may want to familiarize yourself with java coding. Even if you already have enough experience with object oriented programming there are still a few tools you must get before you begin coding.<br>
<br>
<h2>Set Up Your Environment</h2>
The primary tool you will use while building an android app is Android SDK(Software Development Kit). SDK has all the features you will need to create,debug and document your apps including libraries.<br>
<br>
Until very recently(2014) Eclipse was the default IDE(integrated development enviroment) for Android which is now replaced with Google developed Android Studio. IDE is the main program you write your code on and both of those two choices have some advantages you may want to consider. Of course there are other enviroments like NetBeans with a plugin which allows development if you prefer them.<br>
<br>
ADB(Android Debug Bridge) is the second tool you should look at as it allows you to connect your android devices with your computer. When connected, it handles communication between server and client side .<br>
<br>
Lastly there is Fastboot which is primarily used to flash filesystem by sending commands to bootloader from your computer .</td>
</tr> </tbody></table>
<br>
<table border='0'> <tbody> <tr><td width='40%' valign='top'><h2>Android Developer Guidelines</h2>When developing an android application, it is important to follow Android's <a href='https://developer.android.com/guide/index.html'>Introduction to Android</a> page to get a key grasp of the various classes and APIs Android provides. These documents house the most commonly used front-end and back-end APIs, giving the developer the proper tools to start writing an application. From thereon, the developer can access the full <a href='https://developer.android.com/reference/packages.html'>references</a> of each and every API Android provides. Through selecting their preferred API level, the developer can easily clear out those API's that are unavailable at that API level, saving them the pain of noticing it much later into development.<br>
<br>
The developer is also encouraged to use the <a href='https://developer.android.com/google/index.html'>Google Services</a> for such things as In-app Billing, Advertisements, and the most famous Google Maps. Google aims to simplify implementation for the developer, and to make the user feel secure, by bearing the burden of those services that the developer opts in to use.<br>
<br>
Last but not least, Android provides many <a href='https://developer.android.com/samples/index.html'>sample projects</a> for its most commonly used APIs. These samples can be obtained by clicking the download links on the browser, or much more easily through importing the samples into the Android Studio through GitHub.<br>
<br>
</td><td width='60%' valign='top'><b>The sweet history of Android</b><pre></pre>
<img src='http://static.ibnlive.in.com/ibnlive/pix/ibnhome/android-versions-twitter.jpg' />
</td></tr><tr><td width='40%' valign='top'><img src='http://developer.android.com/design/media/design_elements_landing.png' /></td>
<td width='60%' valign='top'><h2>Android Design Guidelines</h2>
Google has defined a very detailed set of design guidelines, aiming to standardize app behaviour throughout the plethora of applications it currently houses at the Google Play Store. It can be effectively split into three:<br>
<ul><li>Simplify the user experience through making sure all actions a user takes while on an application is as intuitive as it can be. Make sure that for any given design pattern, you are consistent both inside your application, and outside of it (if it is a common enough pattern to be in <a href='https://developer.android.com/design/patterns/index.html'>Android Design Patterns</a>).<br>
</li><li>Beautify the user experience through subtle animations. These may be bumps, overshoots, or any other animations that complement the user's actions. Any application should respond to user inputs, making sure that the user knows his inputs are understood<br>
</li><li>Enhance the user experience through doing the menial work. When possible, make sure the users have fast and easy access to those features of your application that they most often use.</li></ul>

Android does not enforce any application developer to follow these rules. However, these guidelines are extremely useful in uniting the user experience across all applications. It is far easier to teach someone to do things once, than to teach them again for every application seperately.<br>
<br>
The newest addition to Android Design is the <a href='https://developer.android.com/design/material/index.html'>Material Design</a>. Coming with Android 5.0, it builds upon Android's previous design guidelines and enhances it to the next level.</td></tr></tbody> </table>

<table border='0'> <tbody> <tr>
<td width='40%' valign='top'> <h2>Open Source Libraries</h2>
One of the greatest arsenal in Android Application development is the Open Source libraries. As of 23/02/15, there are 176,653 repositories tagged with /android. There is a huge open source contribution and If you are up to Android Application Development, you should check out some popular libraries like <a href='https://github.com/JakeWharton/butterknife'>Butter Knife</a> or <a href='https://github.com/square/picasso'>Picasso</a>. These libraries are well known injection libraries which not only shorten the execution time, they cut down on code writing time as well.<br>
<br>
</td>
<td width='40%' valign='top'> <a href='https://github.com/JakeWharton/butterknife'><b>Butter Knife</b></a><pre></pre><img src='https://raw.githubusercontent.com/JakeWharton/butterknife/master/website/static/logo.png' /> </td>
</tr>
<tr>
<td width='20%' valign='top'>
<a href='https://github.com/square/picasso'><b>Picasso</b></a><pre></pre>
<img src='http://square.github.io/picasso/static/sample.png' />
</td>
<td width='30%' valign='top'>
<h2>Popular Android Applications</h2>
Since Adroid is the most used mobil OS and it is installed almost in 1 billion devices, issue most popular android devices is an important research field. A developer who wants to work on android must be aware of popular apps and their characteristics.<br>
<br>
Here is the 10 most popular applications:<br>
<br>
<b>Application Developer</b><pre></pre>
1. Gmail	         <pre></pre>
2. Chrome Browser       <pre></pre>
3. Angry Birds	        <pre></pre>
4. Facebook	        <pre></pre>
5. Skype	        <pre></pre>
6. Twitter	        <pre></pre>
7. WhatsApp Messenger	 <pre></pre>
8. Adobe Flash Player 	<pre></pre>
9. Facebook Messenger	<pre></pre>
10. Fruit Ninja Free	<pre></pre>
</td>
</tr>
</tbody> </table>

<h1>Resources</h1>
<ul><li><a href='http://lifehacker.com/i-want-to-write-android-apps-where-do-i-start-1643818268'>http://lifehacker.com/i-want-to-write-android-apps-where-do-i-start-1643818268</a>
</li><li><a href='http://en.wikipedia.org/wiki/Android_(operating_system'>http://en.wikipedia.org/wiki/Android_(operating_system</a>)<br>
</li><li><a href='http://www.cyanogenmod.org/'>http://www.cyanogenmod.org/</a>
</li><li><a href='https://source.android.com/'>https://source.android.com/</a>
</li><li><a href='https://github.com/JakeWharton/butterknife'>https://github.com/JakeWharton/butterknife</a>
</li><li><a href='https://github.com/square/picasso'>https://github.com/square/picasso</a></li></ul>

<h1>Research Group</h1>

<ul><li><a href='BurakTepedelen.md'>Burak Tepedelen</a>
</li><li><a href='DorukKilitcioglu.md'>Doruk Kilitçioğlu</a>
</li><li><a href='MertYasin.md'>Mert Yaşin</a>
</li><li><a href='MuhammedAliArslanbenzer.md'>Muhammed Ali Arslanbenzer</a>