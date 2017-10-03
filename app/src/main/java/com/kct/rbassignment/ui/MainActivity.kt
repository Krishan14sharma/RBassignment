package com.kct.rbassignment.ui

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.kct.rbassignment.BaseApp
import com.kct.rbassignment.R
import com.kct.rbassignment.repository.api.Owner
import com.kct.rbassignment.repository.api.Repo
import com.kct.rbassignment.util.TabLayoutSelectedHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import javax.inject.Inject


/**
 * Created by krishan on 02/10/17.
 */

class MainActivity : AppCompatActivity(), MainActivityScreen,
        GitHubListAdapter.RepoListProvider, GithubOwnerFragment.GithubOwnerProvider {

    @Inject
    lateinit var presenter: MainActivityPresenter
    var height: Int = 0
    lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as BaseApp).appComponent.inject(this)
        presenter.bind(this)
        height = getHeightOfScreen()

        bottomSheetBehavior = BottomSheetBehavior.from(bottomContainer)
        bottomSheetBehavior.peekHeight = resources.getDimensionPixelSize(R.dimen.peek_height)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED || newState == BottomSheetBehavior.STATE_DRAGGING) {
                    bottomSheetBehavior.peekHeight = resources.getDimensionPixelSize(R.dimen.peek_height)
                }
            }

        })

        presenter.getGithubData()
    }

    private fun getHeightOfScreen(): Int {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.y
    }

    override fun getRepoList(): List<Repo> {
        return presenter.uiModel.list
    }

    override fun getOwner(): Owner {
        return presenter.uiModel.owner
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.unBind()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showData() {
        viewPager.adapter = ViewPagerAdapter(this, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.addOnTabSelectedListener(object : TabLayoutSelectedHelper() {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.peekHeight = height / 2
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.peekHeight = height / 2
                }
            }
        })

    }

    override fun showError() {
        longToast(getString(R.string.network_error))
    }

    internal class ViewPagerAdapter(val context: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> GithubListFragment.newInstance()
                1 -> GithubOwnerFragment.newInstance()
                else -> {
                    throw IllegalStateException("Fragment not defined for this $position position")
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> context.getString(R.string.git_repository)
                1 -> context.getString(R.string.git_owner)
                else -> {
                    throw IllegalStateException("Title not defined for this $position position")
                }
            }
        }
    }
}


