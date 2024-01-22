package com.example.ricelimepredictionapp.ui.dashboard.camera

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ricelimepredictionapp.R
import com.example.ricelimepredictionapp.ui.theme.orange
import com.example.ricelimepredictionapp.ui.theme.white
import com.example.ricelimepredictionapp.ui.view_model.LoginViewModel
import com.example.ricelimepredictionapp.ui.view_model.PostViewModel
import com.example.ricelimepredictionapp.ui.view_model.ViewModelFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


const val requestCode = 123
@Composable
fun Camera(viewModel: PostViewModel, navController: NavController) {

    // Menggunakan applicationContext

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val applicationContext = context.applicationContext
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcherGallery = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }

    val launcherCamera = rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) { isSuccess: Boolean ->
        if (isSuccess) {
            imageUri?.let { uri ->
                // Save the image to the gallery
                saveImageToGallery(context, uri)
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        val permissions = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )

        val permissionGranted = permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }

        if (!permissionGranted) {
            ActivityCompat.requestPermissions(
                (context as ComponentActivity),
                permissions,
                requestCode
            )
        } else {
            // Permissions are granted, proceed with camera launch or image capture logic
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Display the taken image
        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->

                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .height(500.dp)
                        .width(400.dp)
                        .padding(20.dp),
                    contentScale = ContentScale.FillWidth

                )

            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Pecentage\t: ")

        Spacer(modifier = Modifier.height(12.dp))

        // Buttons for picking image or taking picture
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { launcherGallery.launch("image/*") }) {
                Text(text = "Pick Image")
            }

            Button(onClick = {
                try {
                    val contentValues = ContentValues().apply {
                        put(MediaStore.Images.Media.TITLE, "New Picture")
                        put(MediaStore.Images.Media.DESCRIPTION, "From Camera")
                    }
                    val imageUriResult: Uri? = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                    imageUriResult?.let { uri ->
                        launcherCamera.launch(uri)
                    }
                } catch (e: Exception) {
                    // Tangani pengecualian
                    Log.e("CameraActivity", "Error capturing image: ${e.message}")
                    Toast.makeText(context, "Error capturing image", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(text = "Take Picture")
            }
            Button(onClick = {
                bitmap.value?.let { btm ->
                    val file = saveBitmapToFile(btm.asImageBitmap().asAndroidBitmap(), applicationContext)
                    file?.let {
                        // File berhasil disimpan, file adalah objek File yang berisi file gambar
                        viewModel.uploadImage(file, "description") { response ->
                            // Handle response di sini
                            val resultImagePath = response?.data?.resultImagePath
                            viewModel.afterImage = resultImagePath.toString();
                            print("saya : ${viewModel.afterImage}")
                            resultImagePath?.let {
                                Log.d("ResultImagePath", it) // Cetak resultImagePath ke Log
                                // Lakukan tindakan lain dengan resultImagePath di sini sesuai kebutuhan Anda
                            } ?: run {
                                Log.d("ResultImagePath", "No result image path") // Jika resultImagePath null
                            }
                        }
                    }
                }
               navController.navigate("after_image")

            }, ) {
                Text(text = "Prediksi")
            }

        }
    }
}

// Function to save the image to the gallery
private fun saveImageToGallery(context: Context, imageUri: Uri) {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.TITLE, "New Picture")
        put(MediaStore.Images.Media.DESCRIPTION, "From Camera")
    }
    context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)?.let { uri ->
        context.contentResolver.openOutputStream(uri)?.use { outputStream ->
            context.contentResolver.openInputStream(imageUri)?.use { inputStream ->
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }
            }
        }
    }
}
@Composable
fun PickImageFromGallery() {

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        imageUri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            bitmap.value?.let { btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(400.dp)
                        .padding(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = { launcher.launch("image/*") }) {
            Text(text = "Pick Image")
        }
    }

}
fun saveBitmapToFile(bitmap: Bitmap, context: Context): File? {
    val fileName = "image_file.jpg" // Ganti dengan nama file yang diinginkan
    val directory = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "YourDirectoryName")

    if (!directory.exists()) {
        directory.mkdirs()
    }

    val file = File(directory, fileName)
    try {
        val fileOutputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
        fileOutputStream.flush()
        fileOutputStream.close()
        return file
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}

@Composable
fun PopupScreenExample() {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Show Popup")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(text = "Popup Title")
                },
                text = {
                    Text(text = "This is a simple popup message.")
                },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    }
}
