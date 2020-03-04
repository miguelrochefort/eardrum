# Eardrum

24/7 background audio recording for Android.

## Instructions

1. Accept permissions:
	* `Manifest.permission.RECORD_AUDIO` allows Eardrum to hear audio.
	* `Manifest.permission.WRITE_EXTERNAL_STORAGE` allows Eardrum to remember audio.

2. AudioRecorderService gets started by:
	* `MainActivity` every time you launch Eardrum.
	* `BootReceiver` every time your device boots.
	* `AlarmManager` every hour on the hour.

3. AudioRecorderService creates a new recording every time it's started:
	* Records audio in the background using your device's main microphone.
	* Writes audio to a file with pattern `eardrum-20070405T12300200.ogg`.
	* Stores the file at `/storage/emulated/0/Android/data/com.miguelrochefort.eardrum/`.
  
  ## Notes
  
  * Only briefly tested on a Nokia 6.1 running Android 10 (Q).
