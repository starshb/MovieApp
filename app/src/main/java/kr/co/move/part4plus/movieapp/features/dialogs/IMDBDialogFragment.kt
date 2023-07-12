package kr.co.move.part4plus.movieapp.features.dialogs

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kr.co.move.part4plus.movieapp.BaseDialogFragment
import kr.co.fastcampus.part4plus.movieapp.R
import kr.co.move.part4plus.movieapp.ui.components.dialog.Default
import kr.co.move.part4plus.movieapp.ui.components.dialog.DialogPopup
import kr.co.move.part4plus.movieapp.ui.models.dialog.DialogButton
import kr.co.move.part4plus.movieapp.ui.theme.MovieAppTheme

@AndroidEntryPoint
class IMDBDialogFragment : BaseDialogFragment() {

    private val args: IMDBDialogFragmentArgs by navArgs()

    val IMDB_BASE_URL = "https://m.imdb.com"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.apply {
            isCancelable = true
            setCanceledOnTouchOutside(true)
            window?.setBackgroundDrawable(ColorDrawable(requireContext().getColor(android.R.color.transparent)))
        }

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MovieAppTheme(
                    themeState = themeViewModel.themeState.collectAsState()
                ) {
                    DialogPopup.Default(
                        title = stringResource(R.string.imdb_title),
                        bodyText = stringResource(R.string.imdb_message),
                        buttons = listOf(
                            DialogButton.Primary(getString(R.string.open)) {
                                startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(IMDB_BASE_URL + args.url)
                                    )
                                )
                            },
                            DialogButton.SecondaryBorderless(getString(R.string.cancel)) {
                                dismiss()
                            }
                        )
                    )
                }
            }
        }
    }
}
