package uz.itschool.docc.screen.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import uz.itschool.docc.model.Card
import uz.itschool.docc.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageChackerUniqueScreen(
    navController: NavController, card: Card,
) {
    var isChecked1 by remember { mutableStateOf(false) }
    var isChecked2 by remember { mutableStateOf(false) }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
       context.packageName + ".provider", file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri = uri
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }


    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = "",
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(top = 50.dp, start = 24.dp)
                    .size(30.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "${card.title} tahlili",
                fontSize = 30.sp,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 24.dp, top = 50.dp)
            )
        }
        Text(
            text = "Rentgenni qaysi kasallikka ko’ra tahlilini xohlaysiz?",
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(start = 24.dp, top = 20.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            Modifier
                .clickable {
                    if (!isChecked1) {
                        isChecked1 = true
                        isChecked2 = false
                    } else {
                        isChecked1 = false
                        isChecked2 = false
                    }
                }
                .align(Alignment.CenterHorizontally)) {
            Card(
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isChecked1) Color(0xFF8D7BFD) else Color.White
                ),
                border = BorderStroke(2.dp, Color(0xFF8D7BFD)),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(0.7f)
            ) {}
            Text(
                text = card.illness1,
                color = if (isChecked1) Color.White else Color(0xFF8D7BFD),
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            Modifier
                .clickable {
                    if (!isChecked2) {
                        isChecked2 = true
                        isChecked1 = false
                    } else {
                        isChecked2 = false
                        isChecked1 = false
                    }
                }
                .align(Alignment.CenterHorizontally)) {
            Card(
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isChecked2) Color(0xFF8D7BFD) else Color.White
                ),
                elevation = CardDefaults.cardElevation(5.dp),
                border = BorderStroke(2.dp, Color(0xFF8D7BFD)),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(0.7f)
            ) {}
            Text(
                text = card.illness2,
                color = if (isChecked2) Color.White else Color(0xFF8D7BFD),
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row() {
            Spacer(modifier = Modifier.width(10.dp))
            Box(Modifier.clickable {
                val permissionCheckResult =
                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    cameraLauncher.launch(uri)
                } else {
                    // Request a permission
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }

            }) {
                Card(
                    shape = RoundedCornerShape(15.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFFFFF)
                    ),
                    elevation = CardDefaults.cardElevation(5.dp),
                    modifier = Modifier
                        .height(50.dp)
                        .width(180.dp)
                        .align(Alignment.Center)
                ) {}
                Row(
                    Modifier
                        .align(Alignment.Center), Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterVertically)
                            .fillMaxHeight(),
                        tint = Color.Black
                    )
                    Text(
                        text = "Rasmga olish",
                        color = Color.Black,
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(Modifier.clickable {
                launcher.launch("image/*")
            }) {
                Card(
                    shape = RoundedCornerShape(15.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF8D7BFD)
                    ),
                    elevation = CardDefaults.cardElevation(5.dp),
                    modifier = Modifier
                        .height(50.dp)
                        .width(180.dp)
                        .align(Alignment.Center)
                ) {}
                Row(modifier = Modifier.align(Alignment.Center)) {
                    Icon(
                        painter = painterResource(id = R.drawable.cloud_download),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .fillMaxHeight()
                            .align(Alignment.CenterVertically),
                        tint = Color.White
                    )
                    Text(
                        text = "Rasm yuklash",
                        color = Color.White,
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
        Spacer(modifier = Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.empty),
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Center)
            )
            if (capturedImageUri.path?.isNotEmpty() == true) {
                Image(
                    modifier = Modifier
                        .width(250.dp)
                        .height(300.dp),
                    contentScale = ContentScale.Crop,
                    painter = rememberImagePainter(capturedImageUri),
                    contentDescription = null
                )
            }
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                modifier = Modifier
                    .width(250.dp)
                    .height(300.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.empty)
            )
        }
    }
}

fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}