package th3g3ntl3m3n.wellapanti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by th3g3ntl3m3n on 8/9/17.
 */

public class FAQ extends Fragment {
    private String[] question = {
            "Will Wellapanti request for my account details especially my password?",
            "Why does the site look different?",
            "Why can’t I find all the images I posted along with my Events?",
            "Why was my events text edited?",
            "Items not allowed in Wellapanti"
    };

    private String[] answers = {
            "Wellapanti will never request for the password to your account on our website. Please beware of anyone posing as an Wellapanti Staff or Agent requesting for your password. Kindly protect the password to your Wellapanti account and do not share this information with anyone.",
            "Due to the recent upgrade of the Wellapant site, there has been significant changes in the  site to further enhance your user experience with us! Our joy is to ensure your knowledge transfer on our platform easily and efficiently.\n" + "Hope you have a pleasant experience buying and selling !",
            "It is important that the images posted are directly relevant to the ad(s) posted and conform with our terms of use at Wellapanti. We are sorry for any inconvenience caused but images which fall in the under-listed category will be removed from events posted:\n" +
                    "Images portraying nudity\n" +
                    "Watermarked Images",
            "The quality of events posted on our platform matter a lot to us. As a result, we are focused on ensuring that events posted have really good all-round quality in terms of image, title and description. A badly, punctuated events, with wrong spelling can really work against an events and sometimes, even change its meaning. We are here to help! We have edited the event and corrected the typographical errors on your behalf.",
            "Firearms and ammunition– Weapons that require a permit or legal authorization are not permitted\n" +
                    "Illegal/Pirated copies– Copies, duplicates or “backup copies” of software or audiovisual items, including memory cards or other storage devices. e.g DVDs, CDs and books\n" +
                    "Drugs, narcotics, tobacco and other smoking related products\n" +
                    "Free to air decoders\n" +
                    "Fireworks and derivatives\n" +
                    "Alcoholic beverages\n" +
                    "All forms of medications\n" +
                    "Pyramid schemes / multi-level marketing\n" +
                    "Animals considered to be endangered under any local or international laws\n" +
                    "Affiliate Programs / rewards or commissions and / or business partnerships\n" +
                    "Offers on how to make money / profitable time / work from home\n" +
                    "Human organs\n" +
                    "Replicas and counterfeits\n" +
                    "Money Transactions –Events or services offering the exchange(either online or otherwise) of any legal tender.\n" +
                    "Tarot Services Horoscopes and / or Voodoo (Black magic)\n" +
                    "Natural products that claim to cure diseases.\n" +
                    "Digital goodsg.ebooks and vouchers in electronic form\n" +
                    "Coupons, vouchers and credit cards\n" +
                    "Equipment for games of chance e.g Lottery & Slot machines\n" +
                    "Explicit or adult content or services\n" +
                    "Ads seeking for donations or help.\n" +
                    "“Selfies” and images that have no relation to the posted item or service\n" +
                    "Images downloaded from the internet (stock images) are not allowed."
    };

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faq_layout, container, false);

        recyclerView = view.findViewById(R.id.faqList);
        adapter = new FAQAdapter(question, answers);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public static class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.ViewHolder> {

        private String[] questions, answers;

        public FAQAdapter(String[] questions, String[] answers) {
            this.questions = questions;
            this.answers = answers;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.header.setText(questions[position]);
            holder.content.setText(answers[position]);
        }

        @Override
        public int getItemCount() {
            return questions.length;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView header, content;

            public ViewHolder(View itemView) {
                super(itemView);
                header = itemView.findViewById(R.id.header);
                content = itemView.findViewById(R.id.content);
            }
        }
    }
}
