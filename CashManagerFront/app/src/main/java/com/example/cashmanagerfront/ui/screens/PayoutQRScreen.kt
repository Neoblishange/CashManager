package com.example.cashmanagerfront.ui.screens

import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.BackpressureStrategy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.PackageManagerCompat
import androidx.navigation.NavHostController
import com.example.cashmanagerfront.QrCodeAnalyzer
import com.example.cashmanagerfront.ui.screens.widgets.AppBarWidget
import com.example.cashmanagerfront.ui.screens.widgets.BackgroundApp
import com.example.cashmanagerfront.ui.screens.widgets.CustomText
import com.example.cashmanagerfront.ui.utils.Strings
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects


@Composable
fun PayoutQRScreen(navController: NavHostController, total: String) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackgroundApp()

        Column(
            modifier = Modifier.fillMaxSize().padding(15.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            AppBarWidget(
                navController = navController,
                title = Strings.APP_BAR_PAYOUT_QR,
                showBackArrow = true,
                showIcon = false
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomText(
                text = Strings.TOTAL_PAYOUT_BODY, size = 26.sp, color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomText(
                text = total + " " + Strings.TOTAL_PAYOUT_CURRENCY,
                color = Color.White,
                size = 40.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            qrCodeScannerComposeTheme()
            Spacer(modifier = Modifier.weight(1f))
        }


    }
}

@Composable
fun qrCodeScannerComposeTheme(){
    var code by  remember() {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val lifeCycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
                )== PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        }
    )
    LaunchedEffect(key1 = true){
        launcher.launch(android.Manifest.permission.CAMERA)
    }

    Column{
        if (hasCamPermission)
        {
            AndroidView(
                factory = { context->
                    val previewView = PreviewView(context)
                    val preview = androidx.camera.core.Preview.Builder().build()
                    val selector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()
                    preview.setSurfaceProvider(previewView.surfaceProvider)
                    val imageAnalysis = ImageAnalysis.Builder()
                        .setTargetResolution(
                            Size(
                                previewView.width,
                                previewView.height
                            )
                        )
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                    imageAnalysis.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        QrCodeAnalyzer{ result ->
                            code = result
                        }
                    )
                    try {
                        cameraProviderFuture.get().bindToLifecycle(
                            lifeCycleOwner,
                            selector,
                            preview,
                            imageAnalysis
                        )

                    } catch (e : Exception){
                        e.printStackTrace()
                    }
                    previewView
            },
               modifier = Modifier
                   .width(250.dp).height(250.dp).align(alignment = Alignment.CenterHorizontally)
                )
            Text(text = code,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color =  Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            )
        }


    }

}

