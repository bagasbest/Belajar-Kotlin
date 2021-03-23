package com.bagasbest.fundamental2.myUnitTest

class MyUnitTestMainViewModel (private val cuboidModel: MyUnitTestCuboidModel) {

    fun getCircumference(): Double = cuboidModel.getCircumference()
    fun getSurfaceArea(): Double = cuboidModel.getSurfaceArea()
    fun getVolume(): Double = cuboidModel.getVolume()

    fun save (l: Double, w: Double, h:Double) {
        cuboidModel.save(l,w,h)
    }

}