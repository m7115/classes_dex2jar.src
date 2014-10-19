package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.okio.ByteString;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class Huffman
{
  private static final int[] REQUEST_CODES = { 134217658, 134217659, 134217660, 134217661, 134217662, 134217663, 134217664, 134217665, 134217666, 134217667, 134217668, 134217669, 134217670, 134217671, 134217672, 134217673, 134217674, 134217675, 134217676, 134217677, 134217678, 134217679, 134217680, 134217681, 134217682, 134217683, 134217684, 134217685, 134217686, 134217687, 134217688, 134217689, 232, 4092, 16378, 32764, 32765, 36, 110, 32766, 2042, 2043, 1018, 2044, 233, 37, 4, 0, 5, 6, 7, 38, 39, 40, 41, 42, 43, 44, 492, 234, 262142, 45, 131068, 493, 16379, 111, 235, 236, 237, 238, 112, 494, 495, 496, 497, 1019, 498, 239, 499, 500, 501, 502, 503, 240, 241, 504, 505, 506, 507, 508, 1020, 16380, 134217690, 8188, 16381, 46, 524286, 8, 47, 9, 48, 1, 49, 50, 51, 10, 113, 114, 11, 52, 12, 13, 14, 242, 15, 16, 17, 53, 115, 54, 243, 244, 245, 131069, 2045, 131070, 4093, 134217691, 134217692, 134217693, 134217694, 134217695, 134217696, 134217697, 134217698, 134217699, 134217700, 134217701, 134217702, 134217703, 134217704, 134217705, 134217706, 134217707, 134217708, 134217709, 134217710, 134217711, 134217712, 134217713, 134217714, 134217715, 134217716, 134217717, 134217718, 134217719, 134217720, 134217721, 134217722, 134217723, 134217724, 134217725, 134217726, 134217727, 67108736, 67108737, 67108738, 67108739, 67108740, 67108741, 67108742, 67108743, 67108744, 67108745, 67108746, 67108747, 67108748, 67108749, 67108750, 67108751, 67108752, 67108753, 67108754, 67108755, 67108756, 67108757, 67108758, 67108759, 67108760, 67108761, 67108762, 67108763, 67108764, 67108765, 67108766, 67108767, 67108768, 67108769, 67108770, 67108771, 67108772, 67108773, 67108774, 67108775, 67108776, 67108777, 67108778, 67108779, 67108780, 67108781, 67108782, 67108783, 67108784, 67108785, 67108786, 67108787, 67108788, 67108789, 67108790, 67108791, 67108792, 67108793, 67108794, 67108795, 67108796, 67108797, 67108798, 67108799, 67108800, 67108801, 67108802, 67108803, 67108804, 67108805, 67108806, 67108807, 67108808, 67108809, 67108810, 67108811, 67108812, 67108813, 67108814, 67108815, 67108816, 67108817, 67108818, 67108819, 67108820, 67108821, 67108822, 67108823, 67108824, 67108825, 67108826, 67108827 };
  private static final byte[] REQUEST_CODE_LENGTHS = { 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 8, 12, 14, 15, 15, 6, 7, 15, 11, 11, 10, 11, 8, 6, 5, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 9, 8, 18, 6, 17, 9, 14, 7, 8, 8, 8, 8, 7, 9, 9, 9, 9, 10, 9, 8, 9, 9, 9, 9, 9, 8, 8, 9, 9, 9, 9, 9, 10, 14, 27, 13, 14, 6, 19, 5, 6, 5, 6, 4, 6, 6, 6, 5, 7, 7, 5, 6, 5, 5, 5, 8, 5, 5, 5, 6, 7, 6, 8, 8, 8, 17, 11, 17, 12, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26 };
  private static final int[] RESPONSE_CODES = { 33554364, 33554365, 33554366, 33554367, 33554368, 33554369, 33554370, 33554371, 33554372, 33554373, 33554374, 33554375, 33554376, 33554377, 33554378, 33554379, 33554380, 33554381, 33554382, 33554383, 33554384, 33554385, 33554386, 33554387, 33554388, 33554389, 33554390, 33554391, 33554392, 33554393, 33554394, 33554395, 0, 4090, 106, 8186, 16380, 492, 1016, 8187, 493, 494, 4091, 2042, 34, 35, 36, 107, 1, 2, 3, 8, 9, 10, 37, 38, 11, 12, 13, 495, 65530, 108, 8188, 4092, 65531, 109, 234, 235, 236, 237, 238, 39, 496, 239, 240, 1017, 497, 40, 241, 242, 498, 1018, 499, 41, 14, 500, 501, 243, 1019, 502, 1020, 2043, 8189, 2044, 32764, 503, 131070, 15, 110, 42, 43, 16, 111, 112, 113, 44, 504, 505, 114, 45, 46, 47, 48, 506, 49, 50, 51, 52, 115, 244, 116, 245, 507, 65532, 16381, 65533, 65534, 33554396, 33554397, 33554398, 33554399, 33554400, 33554401, 33554402, 33554403, 33554404, 33554405, 33554406, 33554407, 33554408, 33554409, 33554410, 33554411, 33554412, 33554413, 33554414, 33554415, 33554416, 33554417, 33554418, 33554419, 33554420, 33554421, 33554422, 33554423, 33554424, 33554425, 33554426, 33554427, 33554428, 33554429, 33554430, 33554431, 16777088, 16777089, 16777090, 16777091, 16777092, 16777093, 16777094, 16777095, 16777096, 16777097, 16777098, 16777099, 16777100, 16777101, 16777102, 16777103, 16777104, 16777105, 16777106, 16777107, 16777108, 16777109, 16777110, 16777111, 16777112, 16777113, 16777114, 16777115, 16777116, 16777117, 16777118, 16777119, 16777120, 16777121, 16777122, 16777123, 16777124, 16777125, 16777126, 16777127, 16777128, 16777129, 16777130, 16777131, 16777132, 16777133, 16777134, 16777135, 16777136, 16777137, 16777138, 16777139, 16777140, 16777141, 16777142, 16777143, 16777144, 16777145, 16777146, 16777147, 16777148, 16777149, 16777150, 16777151, 16777152, 16777153, 16777154, 16777155, 16777156, 16777157, 16777158, 16777159, 16777160, 16777161, 16777162, 16777163, 16777164, 16777165, 16777166, 16777167, 16777168, 16777169, 16777170, 16777171, 16777172, 16777173, 16777174, 16777175, 16777176, 16777177, 16777178, 16777179, 16777180 };
  private static final byte[] RESPONSE_CODE_LENGTHS = { 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 4, 12, 7, 13, 14, 9, 10, 13, 9, 9, 12, 11, 6, 6, 6, 7, 4, 4, 4, 5, 5, 5, 6, 6, 5, 5, 5, 9, 16, 7, 13, 12, 16, 7, 8, 8, 8, 8, 8, 6, 9, 8, 8, 10, 9, 6, 8, 8, 9, 10, 9, 6, 5, 9, 9, 8, 10, 9, 10, 11, 13, 11, 15, 9, 17, 5, 7, 6, 6, 5, 7, 7, 7, 6, 9, 9, 7, 6, 6, 6, 6, 9, 6, 6, 6, 6, 7, 8, 7, 8, 9, 16, 14, 16, 16, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24 };

  static enum Codec
  {
    private final int[] codes;
    private final byte[] lengths;
    private final Huffman.Node root = new Huffman.Node();

    static
    {
      Codec[] arrayOfCodec = new Codec[2];
      arrayOfCodec[0] = REQUEST;
      arrayOfCodec[1] = RESPONSE;
      $VALUES = arrayOfCodec;
    }

    private Codec(int[] paramArrayOfInt, byte[] paramArrayOfByte)
    {
      buildTree(paramArrayOfInt, paramArrayOfByte);
      this.codes = paramArrayOfInt;
      this.lengths = paramArrayOfByte;
    }

    private void addCode(int paramInt1, int paramInt2, byte paramByte)
    {
      Huffman.Node localNode1 = new Huffman.Node(paramInt1, paramByte);
      int n;
      for (Huffman.Node localNode2 = this.root; paramByte > 8; localNode2 = Huffman.Node.access$400(localNode2)[n])
      {
        paramByte = (byte)(paramByte - 8);
        n = 0xFF & paramInt2 >>> paramByte;
        if (Huffman.Node.access$400(localNode2) == null)
          throw new IllegalStateException("invalid dictionary: prefix not unique");
        if (Huffman.Node.access$400(localNode2)[n] != null)
          continue;
        Huffman.Node.access$400(localNode2)[n] = new Huffman.Node();
      }
      int i = 8 - paramByte;
      int j = 0xFF & paramInt2 << i;
      int k = 1 << i;
      for (int m = j; m < j + k; m++)
        Huffman.Node.access$400(localNode2)[m] = localNode1;
    }

    private void buildTree(int[] paramArrayOfInt, byte[] paramArrayOfByte)
    {
      for (int i = 0; i < paramArrayOfByte.length; i++)
        addCode(i, paramArrayOfInt[i], paramArrayOfByte[i]);
    }

    ByteString decode(ByteString paramByteString)
      throws IOException
    {
      return ByteString.of(decode(paramByteString.toByteArray()));
    }

    byte[] decode(byte[] paramArrayOfByte)
      throws IOException
    {
      int i = 0;
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      Huffman.Node localNode1 = this.root;
      int j = 0;
      Huffman.Node localNode2 = localNode1;
      int k = 0;
      while (i < paramArrayOfByte.length)
      {
        j = 0xFF & paramArrayOfByte[i] | j << 8;
        k += 8;
        while (k >= 8)
        {
          int n = 0xFF & j >>> k - 8;
          localNode2 = Huffman.Node.access$400(localNode2)[n];
          if (Huffman.Node.access$400(localNode2) == null)
          {
            localByteArrayOutputStream.write(Huffman.Node.access$500(localNode2));
            k -= Huffman.Node.access$600(localNode2);
            localNode2 = this.root;
            continue;
          }
          k -= 8;
        }
        i++;
      }
      Huffman.Node localNode3;
      do
      {
        localByteArrayOutputStream.write(Huffman.Node.access$500(localNode3));
        k -= Huffman.Node.access$600(localNode3);
        localNode2 = this.root;
        if (k <= 0)
          break;
        int m = 0xFF & j << 8 - k;
        localNode3 = Huffman.Node.access$400(localNode2)[m];
      }
      while ((Huffman.Node.access$400(localNode3) == null) && (Huffman.Node.access$600(localNode3) <= k));
      return localByteArrayOutputStream.toByteArray();
    }

    void encode(byte[] paramArrayOfByte, OutputStream paramOutputStream)
      throws IOException
    {
      int i = 0;
      long l = 0L;
      int j = 0;
      while (i < paramArrayOfByte.length)
      {
        int k = 0xFF & paramArrayOfByte[i];
        int m = this.codes[k];
        int n = this.lengths[k];
        l = l << n | m;
        j += n;
        while (j >= 8)
        {
          j -= 8;
          paramOutputStream.write((int)(l >> j));
        }
        i++;
      }
      if (j > 0)
        paramOutputStream.write((int)(l << 8 - j | 255 >>> j));
    }

    int encodedLength(byte[] paramArrayOfByte)
    {
      long l = 0L;
      for (int i = 0; i < paramArrayOfByte.length; i++)
      {
        int j = 0xFF & paramArrayOfByte[i];
        l += this.lengths[j];
      }
      return (int)(l + 7L >> 3);
    }
  }

  private static final class Node
  {
    private final Node[] children;
    private final int symbol;
    private final int terminalBits;

    Node()
    {
      this.children = new Node[256];
      this.symbol = 0;
      this.terminalBits = 0;
    }

    Node(int paramInt1, int paramInt2)
    {
      this.children = null;
      this.symbol = paramInt1;
      int i = paramInt2 & 0x7;
      if (i == 0)
        i = 8;
      this.terminalBits = i;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.spdy.Huffman
 * JD-Core Version:    0.6.0
 */