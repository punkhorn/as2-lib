/**
 * The FreeBSD Copyright
 * Copyright 1994-2008 The FreeBSD Project. All rights reserved.
 * Copyright (C) 2013-2017 Philip Helger philip[at]helger[dot]com
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *    1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE FREEBSD PROJECT ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE FREEBSD PROJECT OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation
 * are those of the authors and should not be interpreted as representing
 * official policies, either expressed or implied, of the FreeBSD Project.
 */
package com.helger.as2lib;

import java.security.SecureRandom;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.random.RandomHelper;
import com.helger.commons.string.StringParser;
import com.helger.commons.system.SystemProperties;

/**
 * This class contains global configuration settings that are system dependent
 * and don't fit in the session object.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class AS2GlobalSettings
{
  /**
   * The default value is defined by the "AS2.useSecureRandom" system property.
   * If not present <code>true</code> is used.
   */
  public static final boolean DEFAULT_USE_SECURE_RANDOM = StringParser.parseBool (SystemProperties.getPropertyValueOrNull ("AS2.useSecureRandom"),
                                                                                  true);

  static
  {
    RandomHelper.setUseSecureRandom (DEFAULT_USE_SECURE_RANDOM);
  }

  private AS2GlobalSettings ()
  {}

  /**
   * Change the usage of SecureRandom if not necessary. On certain Linux
   * derivates the usage of (Very)SecureRandom can take minutes to initialize,
   * because the calls to /dev/urandom are blocking.
   *
   * @param bUseSecureRandom
   *        <code>true</code> to enable it, <code>false</code> to disable it.
   */
  @Deprecated
  public static void setUseSecureRandom (final boolean bUseSecureRandom)
  {
    RandomHelper.setUseSecureRandom (bUseSecureRandom);
  }

  /**
   * @return <code>true</code> if SecureRandom is used where possible,
   *         <code>false</code> to use Random or the like.
   */
  @Deprecated
  public static boolean isUseSecureRandom ()
  {
    return RandomHelper.isUseSecureRandom ();
  }

  @Nonnull
  public static Random getRandom ()
  {
    return RandomHelper.getRandom ();
  }

  @Nullable
  public static SecureRandom getSecureRandom ()
  {
    return RandomHelper.getSecureRandom ();
  }
}
