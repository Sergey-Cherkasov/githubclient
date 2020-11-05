package br.svcdev.githubclient;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import br.svcdev.githubclient.databinding.ActivityMainBinding;
import br.svcdev.githubclient.presenter.Presenter;
import br.svcdev.githubclient.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

	private Presenter presenter;
	private ActivityMainBinding mBinding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBinding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(mBinding.getRoot());

		init();
	}

	private void init() {
		presenter = new Presenter(this);

		mBinding.btnCounter1.setTag(0);
		mBinding.btnCounter2.setTag(1);
		mBinding.btnCounter3.setTag(2);

		mBinding.btnCounter1.setOnClickListener(this);
		mBinding.btnCounter2.setOnClickListener(this);
		mBinding.btnCounter3.setOnClickListener(this);
	}

	@Override
	public void setButtonText(int index, String text) {
		Button button = mBinding.getRoot().findViewWithTag(index);
		button.setText(text);
	}

	@Override
	public void onClick(View view) {
		presenter.onCounterClick((int) view.getTag());
	}
}