package com.meihuayishu.vone.UTILS;

public class XTEA {
    private static final int BLOCK_ALIGN = 8;  
    private static final int KEY_LENGTH = 16;  
    private static final int DELTA = 0x9E3779B9;  
    private static final int NUM_ROUNDS = 64;  
    private int[] keys = new int[NUM_ROUNDS];  
    private byte[] ivtmp = new byte[BLOCK_ALIGN];  
  
    private void setKey(byte[] b) {  
        int[] key = new int[4];  
        for (int i = 0; i < KEY_LENGTH;) {  
            key[i / 4] = (b[i++] << 24) + ((b[i++] & 255) << 16) + ((b[i++] & 255) << 8) + (b[i++] & 255);  
        }  
        for (int i = 0, sum = 0; i < NUM_ROUNDS;) {  
            keys[i++] = sum + key[sum & 3];  
            sum += DELTA;  
            keys[i++] = sum + key[(sum >>> 11) & 3];  
        }  
    }  
  
    private void setIV(byte[] b) {  
        System.arraycopy(b, 0, ivtmp, 0, BLOCK_ALIGN);  
    }  
  
    private byte[] padding(byte[] in, int len) {  
        int lennew = len;  
        int mod = len % BLOCK_ALIGN;  
        if (mod != 0) {  
            lennew = ((len + BLOCK_ALIGN) / BLOCK_ALIGN) * BLOCK_ALIGN;  
        }  
        byte[] innew = new byte[lennew];  
        System.arraycopy(in, 0, innew, 0, len);  
  
        return innew;  
    }  
  
    public byte[] encrypt(byte[] key, byte[] iv, byte[] in, int len) {  
        if (key.length != KEY_LENGTH) {  
            throw new RuntimeException("invalid key length, should be length of 16");  
        }  
        if (iv.length % BLOCK_ALIGN != 0) {  
            throw new RuntimeException("invalid iv length, should be length of 8");  
        }  
        setKey(key);  
        setIV(iv);  
  
        byte[] plain = padding(in, len);  
  
        for (int i = 0; i < plain.length; i += BLOCK_ALIGN) {  
            for (int j = 0; j < BLOCK_ALIGN; j++) {  
                plain[i + j] = (byte) (plain[i + j] ^ ivtmp[j]);  
            }  
            encryptBlock(plain, plain, i);  
            System.arraycopy(plain, i, ivtmp, 0, BLOCK_ALIGN);  
        }  
        return plain;  
    }  
  
    public byte[] decrypt(byte[] key, byte[] iv, byte[] in, int len) {  
        if (key.length != KEY_LENGTH) {  
            throw new RuntimeException("invalid key length, should be length of 16");  
        }  
        if (iv.length % BLOCK_ALIGN != 0) {  
            throw new RuntimeException("invalid iv length, should be length of 8");  
        }  
        if (len % BLOCK_ALIGN != 0) {  
            throw new RuntimeException("invalid ciper length, should be multiple of 8");  
        }  
        setKey(key);  
        setIV(iv);  
  
        byte[] plain = new byte[len];  
        byte[] tmp8 = new byte[BLOCK_ALIGN];  
        for (int i = 0; i < len; i += BLOCK_ALIGN) {  
            System.arraycopy(in, i, tmp8, 0, BLOCK_ALIGN);  
            decryptBlock(in, plain, i);  
            for (int j = 0; j < BLOCK_ALIGN; j++) {  
                plain[i + j] = (byte) (plain[i + j] ^ ivtmp[j]);  
            }  
            System.arraycopy(tmp8, 0, ivtmp, 0, BLOCK_ALIGN);  
        }  
        return plain;  
    }  
  
    private void encryptBlock(byte[] in, byte[] out, int off) {  
        int y = (in[off] << 24) | ((in[off + 1] & 255) << 16) | ((in[off + 2] & 255) << 8) | (in[off + 3] & 255);  
        int z = (in[off + 4] << 24) | ((in[off + 5] & 255) << 16) | ((in[off + 6] & 255) << 8) | (in[off + 7] & 255);  
        for (int i = 0; i < NUM_ROUNDS; i += 2) {  
            y += (((z << 4) ^ (z >>> 5)) + z) ^ keys[i];  
            z += (((y >>> 5) ^ (y << 4)) + y) ^ keys[i + 1];  
        }  
        out[off] = (byte) (y >> 24);  
        out[off + 1] = (byte) (y >> 16);  
        out[off + 2] = (byte) (y >> 8);  
        out[off + 3] = (byte) y;  
        out[off + 4] = (byte) (z >> 24);  
        out[off + 5] = (byte) (z >> 16);  
        out[off + 6] = (byte) (z >> 8);  
        out[off + 7] = (byte) z;  
    }  
  
    private void decryptBlock(byte[] in, byte[] out, int off) {  
        int y = (in[off] << 24) | ((in[off + 1] & 255) << 16) | ((in[off + 2] & 255) << 8) | (in[off + 3] & 255);  
        int z = (in[off + 4] << 24) | ((in[off + 5] & 255) << 16) | ((in[off + 6] & 255) << 8) | (in[off + 7] & 255);  
        for (int i = NUM_ROUNDS - 1; i >= 0; i -= 2) {  
            z -= (((y >>> 5) ^ (y << 4)) + y) ^ keys[i];  
            y -= (((z << 4) ^ (z >>> 5)) + z) ^ keys[i - 1];  
        }  
        out[off] = (byte) (y >> 24);  
        out[off + 1] = (byte) (y >> 16);  
        out[off + 2] = (byte) (y >> 8);  
        out[off + 3] = (byte) y;  
        out[off + 4] = (byte) (z >> 24);  
        out[off + 5] = (byte) (z >> 16);  
        out[off + 6] = (byte) (z >> 8);  
        out[off + 7] = (byte) z;  
    }

    public static void main(String[] args) {

    }
}  