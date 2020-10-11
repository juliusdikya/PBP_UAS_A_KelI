package tugas.besar;

import android.widget.ImageView;
import androidx.databinding.BaseObservable;

import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class Motor{
    public String namaMotor;
    public int hargaSewa;
    public String imgUrl;

    public Motor(String namaMotor, int hargaSewa, String imgURL) {
        this.namaMotor = namaMotor;
        this.hargaSewa = hargaSewa;
        this.imgUrl = imgURL;
    }

    public String getNamaMotor() {
        return namaMotor;
    }

    public void setNamaMotor(String namaMotor) {
        this.namaMotor = namaMotor;
    }

    public int getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(int hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

    public String getImgURL() {
        return imgUrl;
    }

    public void setImgURL(String imgURL) {
        this.imgUrl = imgURL;
    }

    @BindingAdapter({ "avatar" })
    public static void loadImage(ImageView imageView, String imgURL) {
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions())
                .load(imgURL)
                .into(imageView);
    }

}

