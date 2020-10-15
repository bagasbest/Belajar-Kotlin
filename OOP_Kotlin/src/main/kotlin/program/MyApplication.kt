package program

import annotations.Fancy

@Fancy(auhtor = "Bagas")
class MyApplication (val name: String, val version: Int) {
    fun info () : String = "Application $name-$version"
}