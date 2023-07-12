package kr.co.move.part4plus.movieapp.features.dialogs

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kr.co.move.part4plus.movieapp.BaseDialogFragment
import kr.co.fastcampus.part4plus.movieapp.R
import kr.co.move.part4plus.movieapp.ui.components.dialog.DialogPopup
import kr.co.move.part4plus.movieapp.ui.components.dialog.Rating
import kr.co.move.part4plus.movieapp.ui.models.buttons.LeadingIconData
import kr.co.move.part4plus.movieapp.ui.models.dialog.DialogButton
import kr.co.move.part4plus.movieapp.ui.theme.MovieAppTheme

@AndroidEntryPoint
class RatingDialogFragment : BaseDialogFragment() {

    private val args: RatingDialogFragmentArgs by navArgs()

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
                    DialogPopup.Rating(
                        movieName = args.movieName,
                        rating = args.rating,
                        buttons = listOf(
                            DialogButton.Primary(
                                title = getString(R.string.submit),
                                leadingIconData = LeadingIconData(
                                    iconDrawable = R.drawable.ic_send,
                                    iconContentDescription = R.string.submit
                                )
                            ) {
                                dismiss()
                            },
                            DialogButton.Secondary(getString(R.string.cancel)) {
                                dismiss()
                            }
                        )
                    )
                }
            }
        }
    }
}
