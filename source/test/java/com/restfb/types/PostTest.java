/*
 * Copyright (c) 2010-2015 Norbert Bartels
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.restfb.types;

import com.restfb.AbstractJsonMapperTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class PostTest extends AbstractJsonMapperTests {

  @Test
  public void checkV2_1() {
    Post examplePost = createJsonMapper().toJavaObject(jsonFromClasspath("v2_1/post-story"), Post.class);
    assertEquals("Tester shared a link.", examplePost.getStory());
    Post.Privacy priv = examplePost.getPrivacy();
    assertEquals("ALL_FRIENDS", priv.getValue());
  }

  @Test
  public void checkV2_1_emptyArrayPost() {
    Post examplePost = createJsonMapper().toJavaObject(jsonFromClasspath("v2_1/post-emptyarray"), Post.class);
    assertNotNull(examplePost.getProperties().get(0));
    assertNull(examplePost.getProperties().get(0).getName());
    assertNull(examplePost.getProperties().get(0).getHref());
    assertNull(examplePost.getProperties().get(0).getText());

    assertNotNull(examplePost.getProperties().get(1));
    assertNotNull(examplePost.getProperties().get(1).getName());
    assertNotNull(examplePost.getProperties().get(1).getHref());
    assertNotNull(examplePost.getProperties().get(1).getText());
  }

  @Test
  public void checkV2_1_noMessageTags() {
    Post examplePost = createJsonMapper().toJavaObject(jsonFromClasspath("v2_1/post-messagetags"), Post.class);
    assertEquals(0, examplePost.getMessageTags().size());
  }

  @Test
  public void checkV2_1_groupPost() {
    Post examplePost = createJsonMapper().toJavaObject(jsonFromClasspath("v2_1/post-group"), Post.class);

    assertNotNull(examplePost.getTo());
    assertEquals(1, examplePost.getTo().size());
  }
  
  @Test
  public void checkV2_1_Likes() {
    Post examplePost = createJsonMapper().toJavaObject(jsonFromClasspath("v2_1/post-messagetags"), Post.class);
    assertEquals(1L, examplePost.getLikes().getData().size());
  }
  
  @Test
  public void checkV2_1_LikesWithTotalCount() {
      Post examplePost = createJsonMapper().toJavaObject(jsonFromClasspath("v2_1/post-with-likes-totalcount"), Post.class);
      assertEquals(33L, examplePost.getLikes().getTotalCount().longValue());
  }
  
  @Test
  public void checkV2_1_PortogueseText() {
      Post examplePost = createJsonMapper().toJavaObject(jsonFromClasspath("v2_1/post-portoguese"), Post.class);
      String message = "Esse carro \u00e9 maravilhoso.Deus n\u00e3o nos desampara.Obrigada Senhor.";
      assertEquals(message, examplePost.getMessage());
  }
  
  @Test
  public void checkV2_3_AdminCreator() {
      Post examplePost = createJsonMapper().toJavaObject(jsonFromClasspath("v2_3/post-admin-creator"), Post.class);
      assertNotNull(examplePost.getAdminCreator());
      assertEquals("Graph API Explorer", examplePost.getAdminCreator().getName());
      assertEquals("145634995501895", examplePost.getAdminCreator().getId());
  }
}
