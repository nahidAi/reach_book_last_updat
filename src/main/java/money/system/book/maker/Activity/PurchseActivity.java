package money.system.book.maker.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import money.system.book.maker.R;
import money.system.book.maker.util.IabHelper;
import money.system.book.maker.util.IabResult;
import money.system.book.maker.util.Inventory;
import money.system.book.maker.util.Purchase;

public class PurchseActivity extends AppCompatActivity implements IabHelper.OnIabSetupFinishedListener, IabHelper.QueryInventoryFinishedListener {
    private IabHelper iabHelper;
    private static final String BOOK_Premium_ID = "mybook";
    private Button buyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchse);
        buyButton = (Button)findViewById(R.id.buy_button);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchasePremiumAccount();
            }
        });
        checkMyAccount();
    }

    @Override
    public void onIabSetupFinished(IabResult result) {
        if (result.isSuccess()) {
            List<String> details = new ArrayList<>();
            details.add(BOOK_Premium_ID);
            iabHelper.queryInventoryAsync(true, details, this);
            Toast.makeText(this, " ارتباط برقرار شد...", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, " ارتباط برقرار نشد...", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onQueryInventoryFinished(IabResult result, Inventory inv) {
        if (result.isSuccess()) {
            Purchase purchase = inv.getPurchase(BOOK_Premium_ID);
            if (purchase != null) {
                Toast.makeText(this, "پرچیز نال نیست و اطلاعاتی داخلش هست", Toast.LENGTH_SHORT).show();
                //  changeToPremiumAccant();

            }else {
                Toast.makeText(this, "پرچیز نال است و خریدی صورت نگرفته است", Toast.LENGTH_SHORT).show();
            }
            }
    }

    public void checkMyAccount() {
        iabHelper = new IabHelper(this, "MIHNMA0GCSqGSIb3DQEBAQUAA4G7ADCBtwKBrwCrEMvCHQR9FsRXZ4mjgJb30rYJqT6A3lKFgQMxRMnKDMvTWHW/IO6GX56WUzz807C5tbdGcFZFGVPzamevjRUMz1iwYhDSyDh3hr5QgZPchUR72GvNVyNOYf+s815XAkKJ1kNFnZII/3t3i1LdX2555NAqfA4kCUpbrX4bqdL+6ZCDsUZHZXruPtz69nCy092GtvgW65+tHWSDJ8/c0Y0WnPzpQapr/XYYkLrnLH8CAwEAAQ==");
        iabHelper.startSetup(this);
    }


    public void purchasePremiumAccount() {
        iabHelper.launchPurchaseFlow(this, BOOK_Premium_ID, 101, new IabHelper.OnIabPurchaseFinishedListener() {
            @Override
            public void onIabPurchaseFinished(IabResult result, Purchase info) {
                if (result.isSuccess()) {
                    if (info != null) {
                        Toast.makeText(PurchseActivity.this,"محصول خریداری شد",Toast.LENGTH_LONG);
                       // changeToPremiumAccant();
                    } else {
                        Toast.makeText(PurchseActivity.this, " متاسفانه خرید انجام نشد", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 101) {
            iabHelper.handleActivityResult(requestCode, resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iabHelper != null) {
            iabHelper.dispose();
            iabHelper = null;
        }
    }
    public void changeToPremiumAccant() {
       // purchase.setVisibility(View.GONE);
    }
}
