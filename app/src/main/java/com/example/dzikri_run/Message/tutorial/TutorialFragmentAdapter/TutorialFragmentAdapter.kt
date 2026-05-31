package com.example.dzikri_run.Message.tutorial.TutorialFragmentAdapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dzikri_run.Message.tutorial.TutorialMessageActivity

class TutorialFragmentAdapter (
    activity: TutorialMessageActivity,
    private val fragments: List<Fragment>
)  : FragmentStateAdapter(activity)  {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}