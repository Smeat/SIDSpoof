# SIDSpoof

My S-ID-Checker App suddenly (after upating to LineageOS 20) stopped working. Even though the checks are really simple root keept getting detected.
Just creating this module was way faster :)

This Xposed module prevents the S-ID-Checker to detect root.
Additionally it allows for a different installation source (e.g. Aurora frontend). Since the apk is signed anyway this check is just stupid.


# Requirements
 - Magisk
 - LSPosed

# Usage

Install the app (from source or apk) and activate the module for "S-ID-Checker" in LSPosed. Make sure you kill the app after activating the module.


# Rant

If any dev of the app might read this: Just stop checking for root. Without these stupid checks I wouldn't need to modify the behaviour.
With that logic you should also prevent Windows from being used.

But by just looking at your websites and apps it is clear that you don't value or understand security at all. Everything you do seems like a very(!) bad phishing attempt. Just take a look at your domain names
