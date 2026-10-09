

Q3: How many years of experience do you have as an Android Developer? Please specify how many years you have worked with Kotlin and Java, and briefly describe the types of mobile applications you have built.
I have over 6 YOE building Android native apps with both languages. I've built apps for different business models. An advertisement app, connecting clients who want advertising services with users who provide them. A parking solution app, which helps locate parking lots in the USA and Canada, make reservations, and open gates via Bluetooth. A mental health app, which helps connect professional therapists and patients through video calls and chat, set goals, track therapy progress, and stream therapy videos. A banking application, where I optimized and refactored high-traffic app flows, implemented secure communications, and helped track attribution to determine the return on investment of product advertising campaigns.

Q4: Do you have experience working with Android Jetpack libraries such as Navigation, Room, Hilt, CameraX, or ML Kit? Please briefly describe which libraries you have used and how you applied them in a project.
Lifecycle, ViewModel architecture component, used in Fragments and Activity, also from Jetpack. Room, manage schema changes effectively with migrations while handling complex object relationships with normalization creating correct 1:1, 1:N and M:M relationships, asynchronous and reactive operations, combined with Paging. Media3 Exoplayer for video playback and efficient resource use. Hilt with KSP, use granular modules interfaces to leverage provides bindings performance and install them in correct component such as singleton, viewmodel and use a scope and unscoped bindings properly

Q5: Do you have hands-on experience with Kotlin Coroutines and asynchronous programming? Please, briefly decribe your experience.
I use dispatchers to run I/O and long-running tasks off the UI thread. I use integrated scopes (viewModelScope, lifecycleScope) for lifecycle-aware execution. I use Compose APIs (rememberCoroutineScope, LaunchedEffect) to start coroutines bound to a scope that manages its children (structured concurrency). I combine task results using async/await, coroutineScope, and supervisorScope. I handle context inheritance and its elements (SupervisorJob/Job, CoroutineExceptionHandler, CoroutineName) when needed. I run data streams in a lifecycle-aware manner across layers using Flows.

I use coroutine builders (launch, async) and scoping (withContext, coroutineScope, supervisorScope). Coroutines are created inside a CoroutineScope, it provides the parent-child relationship that structured concurrency uses to manage their lifecycle, it is also important to know the cancellation paradigms, parent jobs can cancel children, exceptions in children can cancel other children and parent and scope, use SupervisorJob to limit error propagation, use correct dispatcher in context, use lifecycleScope and viewModelScope in Android, and I use cold and hot flows for reactive data streams

Q6: Do you have experience integrating REST APIs and managing Android application releases through the Google Play Store? Please briefly describe the types of APIs you have integrated and your involvement in app testing, releases, or production support.
Integrated clients (Ktor, Retrofit, OkHttp) over HTTPS with serialization (Kotlinx Serialization, Moshi, Gson). Created secure communications with hybrid encryption. Added well-defined response codes, debuggability, and resilient error handling across layers. Maintained an active session with refresh logic (refresh and access tokens). Published apps on the Play Store, added release notes and listings, and ensured policy compliance. Configured internal testing, app performance and crash observability with Firebase. Set up unit tests using mocks and fakes to test behavior and state.

# Encryption
The android app sandboxing model makes our files secure, another app just can't access our files 
(database, SharedPreferences, DataStore). But also, Android encrypts these files at an OS level. 
However, there are different ways to get these files, before looking at these alternatives you
should know that to get access to these files we need a rooted device, and unlock the boot loader(
this is done with the purpose of installing tools like Frida or Magisk), also know that when a 
device is rooted an attacker doesn't need the app to get to the files at all, it can simply read
them from the file system, however Frida is a special case, this tool will actually require the app
running to be able to inject code that can return us the files, however these tools are not usually
used for doing this type of attack, what they actually are used for is: 1) Reverse-engineer your app,
2) Bypass SSL pinning, 3) Bypass client-side checks (checks that do root detection, paywalls or premium 
flags, license checks, game anti-cheat, fake GPS, KYC or liveness checks). 4) Extract secrets shipped 
in the APK, 5) Commit fraud at scale like bonus or referral abuse, bots, automated account creation, 
replayed requests. This means their resulting attacks are against your backend and all your users, 
but not stolen phones. In stolen phones you can actually use the app itself or, if the attacker needs,
access the files with one of the following methods(without rooting the device):

1. if a device is stolen an attacker could perform a backup and transfer the files to a rooted device
   they control, and then restore them. 
2. Forensic extraction tools like Cellebrite and GrayKey exploit AFU-state(After First Unlock, 
   OS level encryption mainly protects against a powered-off or freshly rebooted stolen phone. It
   does nothing against a privileged attacker on a running unlocked device as after first unlock
   after boot/restart/start, keys used to decrypt files are loaded and since decryption happens at 
   kernel level any process with root privileges will get the files in plain text) or bootloader 
   bugs to dump the file system. Law enforcement and border agencies use these, and so do thieves 
   with resources. Is Attacker’s way to get root without the owner’s cooperation, usually temporary 
   and silent, by abusing a bug instead of the official unlock path.
Version 1.3.0-alpha07
March 11, 2026

A WebView with file access enabled (setAllowFileAccess, setAllowFileAccessFromFileURLs) loading attacker-influenced URLs