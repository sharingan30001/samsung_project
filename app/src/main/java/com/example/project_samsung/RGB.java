package com.example.project_samsung;

import static android.widget.Toast.LENGTH_LONG;

import android.text.GetChars;
import android.widget.Toast;

public class RGB {

    int R = 0, G = 0, B = 0;

    public RGB(int R, int G, int B) {
        this.R = R;
        this.G = G;
        this.B = B;
    }

    public RGB() {}
    public RGB get_RGB_from_CMYK(int C, int M, int Y, int K) {
        int R = 255 * (1 - C) * (1 - K);
        int G = 255 * (1 - M) * (1 - K);
        int B = 255 * (1 - Y) * (1 - K);
        return new RGB(R, G, B);
    }
    private double update(double x) {
        if (x < 0)
            return x + 1;
        if (x > 1)
            return x - 1;
        return x;
    }
    private double get_ans(double x, double P, double Q) {
        if (x < 0.16666)
            return P + ((Q - P) * 6 * x);
        if (0.16666 <= x && x < 0.5)
            return Q;
        if (0.5 <= x && x < 0.66666)
            return P + ((Q - P) * (0.66666 - x) * 6);
        return P;
    }
    public RGB get_RGB_from_HSL(double H, double S, double L) {
        S /= 100;
        L /= 100;
        double Q = (L < 0.5f ? L * (1.0 + S) : L + S - (L * S));
        double P = 2.0 * L - Q;
        double Hk = H / 360;
        double TR = Hk + 0.33333;
        double TG = Hk;
        double TB = Hk - 0.33333;
        TR = update(TR);
        TG = update(TG);
        TB = update(TB);
        int R = (int) ((get_ans(TR, P, Q)) * 255);
        int G = (int) ((get_ans(TG, P, Q)) * 255);
        int B = (int) ((get_ans(TB, P, Q)) * 255);
        return new RGB(R, G, B);
    }
    public RGB get_RGB_from_HEX(String[] f) {
        int R = Integer.parseInt(f[0], 16);
        int G = Integer.parseInt(f[1], 16);
        int B = Integer.parseInt(f[2], 16);
        return new RGB(R, G, B);
    }
}
