
https://m3.material.io/foundations/customization
https://m3.material.io/styles/color/roles
https://github.com/android/compose-samples/blob/main/Jetsnack/app/src/main/java/com/example/jetsnack/ui/components/Button.kt
https://developer.android.com/develop/ui/compose/styles/theming

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
   with resources. It is the Attacker’s way to get root without the owner’s cooperation, usually 
   temporary and silent, by abusing a bug instead of the official unlock path. Existing exploits
   abuse flaws in the Kernel, GPU and drivers, etc. Exploits are patchable bugs tied to a specific 
   model and patch level. Once Google or the vendor ships a fix in a monthly security update, that 
   exploit stops working on updated devices. An up to date phone is hard exploit as latest patch 
   levels have no public or known-private exploit at all. While A budget phone, or one a year or two 
   behind on updates, is often trivial, because known public exploits still work. These attacks are 
   performed through physical access (USB), but they can be remote and Zero-click using commercial 
   spyware like Pegasus (NSO) and Predator (Intellexa) but they are expensive.
3. Your own app leaking them, for example A WebView with file access enabled (`setAllowFileAccess`, 
   `setAllowFileAccessFromFileURLs`) loading attacker-influenced URLs
4. Not reading the file at all. Accessibility-abusing malware reads values off the screen as your 
   app displays them, so neither encryption nor root detection matters.

## Remediation
For protecting Data store files you can protect your app with these methods:

1. Encrypt with a hardware-backed Keystore key. This defeats backups, restores and forensic dumps.
2. Exclude those files from backup and transfer.
3. Keep tokens short-lived and use Play Integrity server-side.
4. For the most sensitive secrets, require user authentication on the key.

Note that rooted device detection fits as one extra signal in that mix. It shouldn’t be your 
protection for files.

## Implementation
Currently I have two options. Use DataStore version `1.3.0-alpha07`, released on March 11, 2026. 
This version integrates with `Tink` to enable encryption, however is still in alpha, and therefore I
might like to use something more stable, in this case that is actually a solution that combines
Android `KeyStore` with `DataStore`, the former provides the encryption/decryption solution and the 
latter just provides the storage solution.
