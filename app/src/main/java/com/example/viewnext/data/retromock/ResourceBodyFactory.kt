package com.example.viewnext.data.retromock

import co.infinum.retromock.BodyFactory
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream


internal class ResourceBodyFactory : BodyFactory {
    @Throws(IOException::class)
    override fun create(input: String): InputStream {
        val javaClass = ResourceBodyFactory::class.java
        val resource = javaClass.getClassLoader()!!.getResource(input).file
        return FileInputStream(resource)
    }
}