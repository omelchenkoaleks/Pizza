package com.omelchenkoaleks.pizza;

import android.graphics.drawable.Drawable;
import android.net.sip.SipSession;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    private Listener listener;

    private String[] captions;
    private int[] imageIds;

    interface Listener {
        void onClick(int position);
    }

    // данные передаются адаптеру в конструкторе
    public CaptionedImagesAdapter(String[] captions, int[] imageIds) {
        this.captions = captions;
        this.imageIds = imageIds;
    }

    // сообщаем адаптеру как нужно создавать объекты
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // код создания экземпляра ViewHolder
        // LayoutInflater преобразует макет в CardView
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cardView);
    }

    // метод вызвается когда нужно использовать ViewHolder для новой порции данных
    // заметка: нужно снабдить параметр position final так как она используется во внутреннем классе
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        CardView cardView = viewHolder.cardView;
        // заполняем данными компонент ImageView
        ImageView imageView = cardView.findViewById(R.id.info_image);
        Drawable drawable =
                ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        // заполняем данными компонент TextView
        TextView textView = cardView.findViewById(R.id.info_text);
        textView.setText(captions[position]);

        // интерфейс добавляется в CardView
        // при щелчке на CardView вызовется метод onClick() интерфейса Listener
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    // этот метод будут использовать активности и фрагменты для регистрация себя в качестве слушателя
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    // сообщаем адаптеру количество элементов данных
    @Override
    public int getItemCount() {
        return captions.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        // в параметрах указан тип данных, которые будут отображаться в RecyclerView
        // каждый объект ViewHolder отображает CardView
        public ViewHolder(CardView view) {
            super(view);
            cardView = view;
        }
    }
}
