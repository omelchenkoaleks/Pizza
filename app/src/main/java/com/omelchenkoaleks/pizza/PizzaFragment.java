package com.omelchenkoaleks.pizza;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PizzaFragment extends Fragment {


    public PizzaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // используем макет RecyclerView
        RecyclerView pizzaRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_pizza, container, false);

        // название видов пиццы добавляем в массив
        String[] pizzaNames = new String[Pizza.pizzas.length];
        for (int i = 0; i < pizzaNames.length; i++) {
            pizzaNames[i] = Pizza.pizzas[i].getName();
        }

        // изображения добавляем в массив
        int[] pizzaImage = new int[Pizza.pizzas.length];
        for (int i = 0; i < pizzaImage.length; i++) {
            pizzaImage[i] = Pizza.pizzas[i].getImageResourceId();
        }

        // массивы передаем адаптеру
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzaNames, pizzaImage);
        pizzaRecycler.setAdapter(adapter);

        // используем вариант размещения карточек в виде двух столбцов (таблица)
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        pizzaRecycler.setLayoutManager(layoutManager);

        // реализуем слушателя и запускаем активность PizzaDatail.Activity, передавая ей
        // идентификатор пиццы, выбранной пользователем
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PizzaDetailActivity.class);
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_ID, position);
                getActivity().startActivity(intent);
            }
        });

        return pizzaRecycler;
    }

}
