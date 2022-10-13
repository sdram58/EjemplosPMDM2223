package com.catata.masterdetailexample.interfaces

import com.catata.masterdetailexample.model.Superhero

interface OnItemClick {
    fun onItemClick(hero: Superhero)
}