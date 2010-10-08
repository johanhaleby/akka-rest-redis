package com.jayway.akkarr

/**
 * Created by IntelliJ IDEA.
 * User: johan
 * Date: Oct 8, 2010
 * Time: 8:02:14 PM
 * To change this template use File | Settings | File Templates.
 */

trait RedisHelpers {
  protected implicit def intToByteArray(in: Int): Array[Byte] = stringToByteArray(in.toString)
  protected implicit def stringToByteArray(in: String): Array[Byte] = in.getBytes("UTF-8")
  protected implicit def asString(in: Array[Byte]): String = new String(in, "UTF-8")
}