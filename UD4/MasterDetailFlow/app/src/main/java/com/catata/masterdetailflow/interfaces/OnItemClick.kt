package com.catata.masterdetailflow.interfaces

import com.catata.masterdetailflow.model.Superhero

interface OnItemClick {
    fun onItemClick(hero: Superhero)
}