
package com.expedia.eps.contentsystem.imgtag.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaceRectangle {
    public int width;

    public int height;

    public int left;

    public int top;
}
