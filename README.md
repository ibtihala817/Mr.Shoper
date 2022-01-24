![Image of Tuwaiq](https://camo.githubusercontent.com/37ca472e2afb74974a0314d89af8f470422a79582bed0d188f9927777230195d/68747470733a2f2f6c61756e63682e73612f6173736574732f696d616765732f6c6f676f732f7475776169712d61636164656d792d6c6f676f2e737667)
# Capstone 3
Tuwaiq Academy Third Project.
ONE Android Application
## LOGO and Overview:
<img width="373" alt="logo" src="https://user-images.githubusercontent.com/91452331/150319291-9fdda635-34d1-4f26-9bb4-5f93efcc108b.png">
This project represents an android application **ONE**, which helps the user display and shop different items. 
## Technologies used:


### This application was built using the following technologies:

### For Designing the logo of the app:


* Canva

### For Designing the pages of the app"
* Figma 

### For Programming the app:
* [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows) for improving code quality.
* Android Architecture Components:Firebase,LiveData,ViewModel and Data binding.
* Firebase
* RecyclerViews & Adapters
* Required Libraries
* Figma
* Mok Api
* Fake Api
* LiveData
* ViewModel
* Data binding 

## Wireframes and User stories:

*Wireframe*

<img width="3281" alt="Frame 10" src="https://user-images.githubusercontent.com/91452331/149739801-97e6e181-e8a3-4caa-89cb-991d3fcb5284.png">


link to Figma [Figma Wireframe](https://www.figma.com/file/QHvjxddhgB8A2wAlkHwUO7/One-App?node-id=0%3A1)///


- As a user,I want to see different products in the app so that I can shop from the app.

- As a user, I would like to be able to share the products so that I can send them to others to view it.

- As a user I want to see the details of each image so that I can see additional information about it (such as title and price).

- As a user I want to have profile so that I can save my information on it.

- as a user I want to add the products to the cart so that I can buy them later.

- as a user I want to download the picture of the products so that I can save them in my gallery.

- as a user I want to delete the products so that I can add other products in the cart.

-------------------------------------------------------------------------
## Installation:
Follow the steps below to get started with the project's development environment:
1. Install Android Studio from [Android Studio](https://developer.android.com/studio?gclid=Cj0KCQjw5oiMBhDtARIsAJi0qk2WOPjxp2Wij5sgO3bAK6Rp18zrs4Y0L5S6W89Fk7OClhAiVuNr1mgaAsT-EALw_wcB&gclsrc=aw.ds)
2. Clone this repository:
 ```kotlin 
 $ git clone https://github.com/ibtihala817/One.git
 ```
3. Navigate to the project directory:
 ```kotlin 
 $ cd One
 ```
 4. List of the depencenceies used in the project:
   * for navigation fragments
 ```kotlin
    dependencies {
    implementation "androidx.navigation:navigation-fragment-ktx:2.3.5"
    implementation "androidx.navigation:navigation-ui-ktx:2.3.5"
    }
``` 
   * for swipe and delete
 ```kotlin
    dependencies {
   implementation 'it.xabaras.android:recyclerview-swipedecorator:1.2.3'
    }
``` 

   * for notification:
```kotlin
    val core_version = "1.6.0"
    dependencies {
    implementation("androidx.core:core-ktx:$core_version")
    }
```
    
   * for ViewModel
```kotlin
    dependencies {
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0-rc01"
    implementation "androidx.fragment:fragment-ktx:1.3.6"
    }
```
   * for live data
```kotlin
    dependencies { 
   implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.4.0-rc01"
   }
```
   * for Image
```kotlin
    dependencies { 
    implementation 'com.squareup.picasso:picasso:2.71828'
   }
```
   * for coroutines
```kotlin
    dependencies { 
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
   }
```

* for Retrofit
```kotlin
    dependencies { 
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.okhttp3:okhttp:4.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
   }
```


* for firebase
```kotlin
    dependencies { 
    implementation 'com.google.firebase:firebase-auth:21.0.1'
    implementation 'com.google.firebase:firebase-auth-ktx'
   }
```


 You are ready to develop!
 -----------------------------------------------------------------
 
## Development Process and Problem-solving Strategy:
*   Firstly, I brainstormed some ideas related to the requirements of the app and then took a general idea of the design and the mechanisim of some popular apps from app store.
*   Secondly, I designed a logo according to the purpose of the app and gave it a name.
*   Thirdly, I designed the screens of each of them suitable for a specific action using the Figma, after that I statred programming the app using the android studio by dividing *   Fourth, I divided the project into several tasks: at first, I installed the required libraries and dependencies and the required api from [FakeApi]  (https://fakestoreapi.com/).
For many problems that I faced, I need first to decided the nature of the error (if it's syntax, runtime or logical), and then find the solution accordingly.Such solutions that can be disovered when debugging the error, using (Log.d) to find the location of the error, searching for similar cases online in [stackoverflow](https://stackoverflow.com/) and asking for the help from the experts.
## Unsolved Problems which would be fixed in future iterations:
* using the firestore for checkout in the cart fragment.
* some minor issues regarding the improvement of the design to make the user expereince more dynamic.
* adding the price, subtotal, charge in the cart.

## My favorite functions work:
* sharing images
it is useful when you want to send a certain image to others.
```kotlin

            val image:Bitmap?= getBitmapFromView(binding.imageItem)
            val share= Intent(Intent.ACTION_SEND)
            share.type="image/*"
            share.putExtra(Intent.EXTRA_STREAM,getImageUri(requireActivity(),image!!))
            startActivity(Intent.createChooser(share, "Share Via:"))

        }

    }

    /// those two functions for sharing the pic

    private fun getBitmapFromView(view: ImageView):Bitmap?{
        val bitmap= Bitmap.createBitmap(view.width,view.height,Bitmap.Config.ARGB_8888)
        val paint=Canvas(bitmap)
        view.draw(paint)
        return bitmap

    }
    private fun getImageUri(inContext:Context, inImage:Bitmap): Uri?{
        val byte=ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG,100,byte)
        val path= MediaStore.Images.Media.insertImage(inContext.contentResolver,inImage,"Title",null)
        return Uri.parse(path)

    }
```  
* download images
```kotlin
 binding.DownloadimageButton.setOnClickListener(){
            val imageUrl = cartItem.image
            val request = DownloadManager.Request(Uri.parse(imageUrl))
                .setTitle("image")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION)
                .setAllowedOverMetered(true)

            val manger = requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            manger.enqueue(request)
        }

