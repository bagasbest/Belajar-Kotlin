package app

import data.BigNote
import data.Node

fun main() {
    val note = Node("Bejar Kotlin")
    println(note.title)

    note.title = ""
    println(note.title)

    val bigNote = BigNote("Belajar kotlin nih")
    println(bigNote.title)
    println(bigNote.bigTitle)
}