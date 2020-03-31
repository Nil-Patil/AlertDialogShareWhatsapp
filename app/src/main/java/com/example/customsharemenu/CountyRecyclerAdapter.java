package com.example.customsharemenu;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CountyRecyclerAdapter extends RecyclerView.Adapter
        <CountyRecyclerAdapter.CountyRecyclerViewHolder> implements Filterable {

    private ArrayList<CountryModel> countyArrayList;
    private ArrayList<CountryModel> countryArrayFiltered;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(CountryModel item);
    }

    CountyRecyclerAdapter(OnItemClickListener listener) {
        this.listener = listener;
        fillCountryList();
        countryArrayFiltered = new ArrayList<>(countyArrayList);
    }

    @NonNull
    @Override
    public CountyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner_country_code_layout,
                parent, false);
        return new CountyRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CountyRecyclerViewHolder holder, int position) {
        holder.bind(countyArrayList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return countyArrayList.size();
    }

    static class CountyRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCountyCode;

        CountyRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCountyCode = itemView.findViewById(R.id.tvCountry);
        }

        @SuppressLint("SetTextI18n")
        void bind(final CountryModel model, final OnItemClickListener listener) {
            txtCountyCode.setText(model.getCountryFlag() + "  " + model.getCountryName() + "  (" + model.getPhoneCode() + ")");
            txtCountyCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(model);
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                List<CountryModel> filteredList = new ArrayList<>();
                if (charSequence == null || charSequence.length() == 0) {
                    filteredList.addAll(countryArrayFiltered);
                } else {
                    String filterPattern = charSequence.toString().toLowerCase().trim();
                    for (CountryModel item : countryArrayFiltered) {
                        if (item.getCountryName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                countyArrayList.clear();
                countyArrayList.addAll((ArrayList) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    private void fillCountryList() {
        //https://emojipedia.org/flags/
        countyArrayList = new ArrayList<>();
        countyArrayList.clear();
        countyArrayList.add(new CountryModel("af", "Afghanistan", "+93", "🇦🇫"));
        countyArrayList.add(new CountryModel("al", "Albania", "+355", "🇦🇱"));
        countyArrayList.add(new CountryModel("dz", "Algeria", "+213", "🇩🇿"));
        countyArrayList.add(new CountryModel("as", "American Samoa", "+1684", "🇦🇸"));
        countyArrayList.add(new CountryModel("ad", "Andorra", "+376", "🇦🇩"));
        countyArrayList.add(new CountryModel("ao", "Angola", "+244", "🇦🇴"));
        countyArrayList.add(new CountryModel("ai", "Anguilla", "+1264", "🇦🇮"));
        countyArrayList.add(new CountryModel("aq", "Antarctica", "+672", "🇦🇶"));
        countyArrayList.add(new CountryModel("ag", "Antigua and Barbuda", "+1268", "🇦🇬"));
        countyArrayList.add(new CountryModel("ar", "Argentina", "+54", "🇦🇷"));
        countyArrayList.add(new CountryModel("am", "Armenia", "+374", "🇦🇲"));
        countyArrayList.add(new CountryModel("aw", "Aruba", "+297", "🇦🇼"));
        countyArrayList.add(new CountryModel("au", "Australia", "+61", "🇦🇺"));
        countyArrayList.add(new CountryModel("at", "Austria", "+43", "🇦🇹"));
        countyArrayList.add(new CountryModel("az", "Azerbaijan", "+994", "🇦🇿"));
        countyArrayList.add(new CountryModel("bs", "Bahamas", "+1242", "🇧🇸"));
        countyArrayList.add(new CountryModel("bh", "Bahrain", "+973", "🇧🇭"));
        countyArrayList.add(new CountryModel("bd", "Bangladesh", "+880", "🇧🇩"));
        countyArrayList.add(new CountryModel("bb", "Barbados", "+1242", "🇧🇧"));
        countyArrayList.add(new CountryModel("by", "Belarus", "+375", "🇧🇾"));
        countyArrayList.add(new CountryModel("be", "Belgium", "+32", "🇧🇪"));
        countyArrayList.add(new CountryModel("bz", "Belize", "+501", "🇧🇿"));
        countyArrayList.add(new CountryModel("bj", "Benin", "+229", "🇧🇯"));
        countyArrayList.add(new CountryModel("bm", "Bermuda", "+1441", "🇧🇲"));
        countyArrayList.add(new CountryModel("bt", "Bhutan", "+975", "🇧🇹"));
        countyArrayList.add(new CountryModel("bo", "Bolivia", "+591", "🇧🇴"));
        countyArrayList.add(new CountryModel("ba", "Bosnia And Herzegovina", "+387", "🇧🇦"));
        countyArrayList.add(new CountryModel("bw", "Botswana", "+267", "🇧🇼"));
        countyArrayList.add(new CountryModel("br", "Brazil", "+55", "🇧🇷"));
        countyArrayList.add(new CountryModel("io", "British Indian Ocean Territory", "+246", "🇮🇴"));
        countyArrayList.add(new CountryModel("vg", "British Virgin Islands", "+1284", "🇻🇬"));
        countyArrayList.add(new CountryModel("bn", "Brunei Darussalam", "+673", "🇧🇳"));
        countyArrayList.add(new CountryModel("bg", "Bulgaria", "+359", "🇧🇬"));
        countyArrayList.add(new CountryModel("bf", "Burkina Faso", "+226", "🇧🇫"));
        countyArrayList.add(new CountryModel("bi", "Burundi", "+257", "🇧🇮"));
        countyArrayList.add(new CountryModel("kh", "Cambodia", "+855", "🇰🇭"));
        countyArrayList.add(new CountryModel("cm", "Cameroon", "+237", "🇨🇲"));
        countyArrayList.add(new CountryModel("ca", "Canada", "+1", "🇨🇦"));
        countyArrayList.add(new CountryModel("cv", "Cape Verde", "+238", "🇨🇻"));
        countyArrayList.add(new CountryModel("ky", "Cayman Islands", "+345", "🇰🇾"));
        countyArrayList.add(new CountryModel("cf", "Central African Republic", "+236", "🇨🇫"));
        countyArrayList.add(new CountryModel("td", "Chad", "+235", "🇹🇩"));
        countyArrayList.add(new CountryModel("cl", "Chile", "+56", "🇨🇱"));
        countyArrayList.add(new CountryModel("cn", "China", "+86", "🇨🇳"));
        countyArrayList.add(new CountryModel("cx", "Christmas Island", "+61", "🇨🇽"));
        countyArrayList.add(new CountryModel("cc", "Cocos (keeling) Islands", "+61", "🇨🇨"));
        countyArrayList.add(new CountryModel("co", "Colombia", "+57", "🇨🇴"));
        countyArrayList.add(new CountryModel("km", "Comoros", "+269", "🇰🇲"));
        countyArrayList.add(new CountryModel("ck", "Cook Islands", "+682", "🇨🇰"));
        countyArrayList.add(new CountryModel("cr", "Costa Rica", "+506", "🇨🇷"));
        countyArrayList.add(new CountryModel("hr", "Croatia", "+385", "🇭🇷"));
        countyArrayList.add(new CountryModel("cu", "Cuba", "+53", "🇨🇺"));
        countyArrayList.add(new CountryModel("cy", "Cyprus", "+357", "🇨🇾"));
        countyArrayList.add(new CountryModel("cz", "Czech Republic", "+420", "🇨🇿"));
        countyArrayList.add(new CountryModel("ci", "Côte D'ivoire", "+225", "🇨🇮"));
        countyArrayList.add(new CountryModel("cd", "Democratic Republic of the Congo", "+243", "🇨🇩"));
        countyArrayList.add(new CountryModel("dk", "Denmark", "+45", "🇩🇰"));
        countyArrayList.add(new CountryModel("dj", "Djibouti", "+253", "🇩🇯"));
        countyArrayList.add(new CountryModel("dm", "Dominica", "+1767", "🇩🇲"));
        countyArrayList.add(new CountryModel("do", "Dominican Republic", "+1849", "🇩🇴"));
        countyArrayList.add(new CountryModel("ec", "Ecuador", "+593", "🇪🇨"));
        countyArrayList.add(new CountryModel("eg", "Egypt", "+20", "🇪🇬"));
        countyArrayList.add(new CountryModel("sv", "El Salvador", "+503", "🇸🇻"));
        countyArrayList.add(new CountryModel("gq", "Equatorial Guinea", "+240", "🇬🇶"));
        countyArrayList.add(new CountryModel("er", "Eritrea", "+291", "🇪🇷"));
        countyArrayList.add(new CountryModel("ee", "Estonia", "+372", "🇪🇪"));
        countyArrayList.add(new CountryModel("et", "Ethiopia", "+251", "🇪🇹"));
        countyArrayList.add(new CountryModel("fk", "Falkland Islands (malvinas)", "+500", "🇫🇰"));
        countyArrayList.add(new CountryModel("fo", "Faroe Islands", "+298", "🇫🇴"));
        countyArrayList.add(new CountryModel("fj", "Fiji", "+679", "🇫🇯"));
        countyArrayList.add(new CountryModel("fi", "Finland", "+358", "🇫🇮"));
        countyArrayList.add(new CountryModel("fr", "France", "+33", "🇫🇷"));
        countyArrayList.add(new CountryModel("gf", "French Guiana", "+594", "🇬🇫"));
        countyArrayList.add(new CountryModel("pf", "French Polynesia", "+689", "🇵🇫"));
        countyArrayList.add(new CountryModel("ga", "Gabon", "+241", "🇬🇦"));
        countyArrayList.add(new CountryModel("gm", "Gambia", "+220", "🇬🇲"));
        countyArrayList.add(new CountryModel("ge", "Georgia", "+995", "🇬🇪"));
        countyArrayList.add(new CountryModel("de", "Germany", "+49", "🇩🇪"));
        countyArrayList.add(new CountryModel("gh", "Ghana", "+233", "🇬🇭"));
        countyArrayList.add(new CountryModel("gi", "Gibraltar", "+350", "🇬🇮"));
        countyArrayList.add(new CountryModel("gr", "Greece", "+30", "🇬🇷"));
        countyArrayList.add(new CountryModel("gl", "Greenland", "+299", "🇬🇱"));
        countyArrayList.add(new CountryModel("gd", "Grenada", "+1473", "🇬🇩"));
        countyArrayList.add(new CountryModel("gp", "Guadeloupe", "+590", "🇬🇵"));
        countyArrayList.add(new CountryModel("gu", "Guam", "+1671", "🇬🇺"));
        countyArrayList.add(new CountryModel("gt", "Guatemala", "+502", "🇬🇹"));
        countyArrayList.add(new CountryModel("gg", "Guernsey", "+44", "🇬🇬"));
        countyArrayList.add(new CountryModel("gn", "Guinea", "+224", "🇬🇳"));
        countyArrayList.add(new CountryModel("gw", "Guinea-Bissau", "+245", "🇬🇼"));
        countyArrayList.add(new CountryModel("gy", "Guyana", "+592", "🇬🇾"));
        countyArrayList.add(new CountryModel("ht", "Haiti", "+509", "🇭🇹"));
        countyArrayList.add(new CountryModel("va", "Holy See (Vatican City State)", "+379", "🇻🇦"));
        countyArrayList.add(new CountryModel("hn", "Honduras", "+504", "🇭🇳"));
        countyArrayList.add(new CountryModel("hk", "Hong Kong", "+852", "🇭🇰"));
        countyArrayList.add(new CountryModel("hu", "Hungary", "+36", "🇭🇺"));
        countyArrayList.add(new CountryModel("is", "Iceland", "+354", "🇮🇸"));
        countyArrayList.add(new CountryModel("in", "India", "+91", "🇮🇳"));
        countyArrayList.add(new CountryModel("id", "Indonesia", "+62", "🇮🇩"));
        countyArrayList.add(new CountryModel("ir", "Iran", "+98", "🇮🇷"));
        countyArrayList.add(new CountryModel("iq", "Iraq", "+964", "🇮🇶"));
        countyArrayList.add(new CountryModel("ie", "Ireland", "+353", "🇮🇪"));
        countyArrayList.add(new CountryModel("im", "Isle Of Man", "+44", "🇮🇲"));
        countyArrayList.add(new CountryModel("il", "Israel", "+972", "🇮🇱"));
        countyArrayList.add(new CountryModel("it", "Italy", "+39", "🇮🇹"));
        countyArrayList.add(new CountryModel("jm", "Jamaica", "+1876", "🇯🇲"));
        countyArrayList.add(new CountryModel("jp", "Japan", "+81", "🇯🇵"));
        countyArrayList.add(new CountryModel("je", "Jersey", "+44", "🇯🇪"));
        countyArrayList.add(new CountryModel("jo", "Jordan", "+962", "🇯🇴"));
        countyArrayList.add(new CountryModel("kz", "Kazakhstan", "+7", "🇰🇿"));
        countyArrayList.add(new CountryModel("ke", "Kenya", "+254", "🇰🇪"));
        countyArrayList.add(new CountryModel("ki", "Kiribati", "+686", "🇰🇮"));
        countyArrayList.add(new CountryModel("xk", "Kosovo", "+383", "🇽🇰"));
        countyArrayList.add(new CountryModel("kw", "Kuwait", "+965", "🇰🇼"));
        countyArrayList.add(new CountryModel("kg", "Kyrgyzstan", "+996", "🇰🇬"));
        countyArrayList.add(new CountryModel("la", "Lao People's Democratic Republic", "+856", "🇱🇦"));
        countyArrayList.add(new CountryModel("lv", "Latvia", "+371", "🇱🇻"));
        countyArrayList.add(new CountryModel("lb", "Lebanon", "+961", "🇱🇧"));
        countyArrayList.add(new CountryModel("ls", "Lesotho", "+266", "🇱🇸"));
        countyArrayList.add(new CountryModel("lr", "Liberia", "+231", "🇱🇷"));
        countyArrayList.add(new CountryModel("ly", "Libya", "+218", "🇱🇾"));
        countyArrayList.add(new CountryModel("li", "Liechtenstein", "+423", "🇱🇮"));
        countyArrayList.add(new CountryModel("lt", "Lithuania", "+370", "🇱🇹"));
        countyArrayList.add(new CountryModel("lu", "Luxembourg", "+352", "🇱🇺"));
        countyArrayList.add(new CountryModel("mo", "Macao Sar China", "+853", "🇲🇴"));
        countyArrayList.add(new CountryModel("mk", "Macedonia", "+389", "🇲🇰"));
        countyArrayList.add(new CountryModel("mg", "Madagascar", "+261", "🇲🇬"));
        countyArrayList.add(new CountryModel("mw", "Malawi", "+265", "🇲🇼"));
        countyArrayList.add(new CountryModel("my", "Malaysia", "+60", "🇲🇾"));
        countyArrayList.add(new CountryModel("mv", "Maldives", "+960", "🇲🇻"));
        countyArrayList.add(new CountryModel("ml", "Mali", "+223", "🇲🇱"));
        countyArrayList.add(new CountryModel("mt", "Malta", "+356", "🇲🇹"));
        countyArrayList.add(new CountryModel("mh", "Marshall Islands", "+692", "🇲🇭"));
        countyArrayList.add(new CountryModel("mq", "Martinique", "+596", "🇲🇶"));
        countyArrayList.add(new CountryModel("mr", "Mauritania", "+222", "🇲🇷"));
        countyArrayList.add(new CountryModel("mu", "Mauritius", "+230", "🇲🇺"));
        countyArrayList.add(new CountryModel("yt", "Mayotte", "+262", "🇾🇹"));
        countyArrayList.add(new CountryModel("mx", "Mexico", "+52", "🇲🇽"));
        countyArrayList.add(new CountryModel("fm", "Micronesia", "+691", "🇫🇲"));
        countyArrayList.add(new CountryModel("md", "Moldova", "+373", "🇲🇩"));
        countyArrayList.add(new CountryModel("mc", "Monaco", "+377", "🇲🇨"));
        countyArrayList.add(new CountryModel("mn", "Mongolia", "+976", "🇲🇳"));
        countyArrayList.add(new CountryModel("me", "Montenegro", "+382", "🇲🇪"));
        countyArrayList.add(new CountryModel("ms", "Montserrat", "+1664", "🇲🇸"));
        countyArrayList.add(new CountryModel("ma", "Morocco", "+212", "🇲🇦"));
        countyArrayList.add(new CountryModel("mz", "Mozambique", "+258", "🇲🇿"));
        countyArrayList.add(new CountryModel("mm", "Myanmar (Burma)", "+95", "🇲🇲"));
        countyArrayList.add(new CountryModel("na", "Namibia", "+264", "🇳🇦"));
        countyArrayList.add(new CountryModel("nr", "Nauru", "+674", "🇳🇷"));
        countyArrayList.add(new CountryModel("np", "Nepal", "+977", "🇳🇵"));
        countyArrayList.add(new CountryModel("nl", "Netherlands", "+31", "🇳🇱"));
        countyArrayList.add(new CountryModel("nc", "New Caledonia", "+687", "🇳🇨"));
        countyArrayList.add(new CountryModel("nz", "New Zealand", "+64", "🇳🇿"));
        countyArrayList.add(new CountryModel("ni", "Nicaragua", "+505", "🇳🇮"));
        countyArrayList.add(new CountryModel("ne", "Niger", "+227", "🇳🇪"));
        countyArrayList.add(new CountryModel("ng", "Nigeria", "+234", "🇳🇬"));
        countyArrayList.add(new CountryModel("nu", "Niue", "+683", "🇳🇺"));
        countyArrayList.add(new CountryModel("nf", "Norfolk Island", "+1670", "🇳🇫"));
        countyArrayList.add(new CountryModel("kp", "North Korea", "+672", "🇰🇵"));
        countyArrayList.add(new CountryModel("mp", "Northern Mariana Islands", "+850", "🇲🇵"));
        countyArrayList.add(new CountryModel("no", "Norway", "+47", "🇳🇴"));
        countyArrayList.add(new CountryModel("om", "Oman", "+968", "🇴🇲"));
        countyArrayList.add(new CountryModel("pk", "Pakistan", "+92", "🇵🇰"));
        countyArrayList.add(new CountryModel("pw", "Palau", "+680", "🇵🇼"));
        countyArrayList.add(new CountryModel("ps", "Palestinian Territory, Occupied", "+970", "🇵🇸"));
        countyArrayList.add(new CountryModel("pa", "Panama", "+507", "🇵🇦"));
        countyArrayList.add(new CountryModel("pg", "Papua New Guinea", "+675", "🇵🇬"));
        countyArrayList.add(new CountryModel("py", "Paraguay", "+595", "🇵🇾"));
        countyArrayList.add(new CountryModel("pe", "Peru", "+51", "🇵🇪"));
        countyArrayList.add(new CountryModel("ph", "Philippines", "+63", "🇵🇭"));
        countyArrayList.add(new CountryModel("pn", "Pitcairn Islands", "+870", "🇵🇳"));
        countyArrayList.add(new CountryModel("pl", "Poland", "+48", "🇵🇱"));
        countyArrayList.add(new CountryModel("pt", "Portugal", "+351", "🇵🇹"));
        countyArrayList.add(new CountryModel("pr", "Puerto Rico", "+1939", "🇵🇷"));
        countyArrayList.add(new CountryModel("qa", "Qatar", "+974", "🇶🇦"));
        countyArrayList.add(new CountryModel("cg", "Republic of the Congo - Brazzaville", "+242", "🇨🇬"));
        countyArrayList.add(new CountryModel("ro", "Romania", "+40", "🇷🇴"));
        countyArrayList.add(new CountryModel("ru", "Russian Federation", "+7", "🇷🇺"));
        countyArrayList.add(new CountryModel("rw", "Rwanda", "+250", "🇷🇼"));
        countyArrayList.add(new CountryModel("re", "Réunion", "+262", "🇷🇪"));
        countyArrayList.add(new CountryModel("bl", "Saint Barthélemy", "+590", "🇧🇱"));
        countyArrayList.add(new CountryModel("sh", "Saint Helena", "+290", "🇸🇭"));
        countyArrayList.add(new CountryModel("kn", "Saint Kitts & Nevis", "+1869", "🇰🇳"));
        countyArrayList.add(new CountryModel("lc", "Saint Lucia", "+1758", "🇱🇨"));
        countyArrayList.add(new CountryModel("mf", "Saint Martin", "+590", "🇲🇫"));
        countyArrayList.add(new CountryModel("pm", "Saint Pierre & Miquelon", "+508", "🇵🇲"));
        countyArrayList.add(new CountryModel("vc", "Saint Vincent & The Grenadines", "+1784", "🇻🇨"));
        countyArrayList.add(new CountryModel("ws", "Samoa", "+685", "🇼🇸"));
        countyArrayList.add(new CountryModel("sm", "San Marino", "+378", "🇸🇲"));
        countyArrayList.add(new CountryModel("st", "Sao Tome & Principe", "+239", "🇸🇹"));
        countyArrayList.add(new CountryModel("sa", "Saudi Arabia", "+966", "🇸🇦"));
        countyArrayList.add(new CountryModel("sn", "Senegal", "+221", "🇸🇳"));
        countyArrayList.add(new CountryModel("rs", "Serbia", "+381", "🇷🇸"));
        countyArrayList.add(new CountryModel("sc", "Seychelles", "+248", "🇸🇨"));
        countyArrayList.add(new CountryModel("sl", "Sierra Leone", "+232", "🇸🇱"));
        countyArrayList.add(new CountryModel("sg", "Singapore", "+65", "🇸🇬"));
        countyArrayList.add(new CountryModel("sx", "Sint Maarten", "+1", "🇸🇽"));
        countyArrayList.add(new CountryModel("sk", "Slovakia", "+421", "🇸🇰"));
        countyArrayList.add(new CountryModel("si", "Slovenia", "+386", "🇸🇮"));
        countyArrayList.add(new CountryModel("sb", "Solomon Islands", "+677", "🇸🇧"));
        countyArrayList.add(new CountryModel("so", "Somalia", "+252", "🇸🇴"));
        countyArrayList.add(new CountryModel("za", "South Africa", "+27", "🇿🇦"));
        countyArrayList.add(new CountryModel("gs", "South Africa (South Georgia & South Sandwich Islands)", "+500", "🇬🇸"));
        countyArrayList.add(new CountryModel("kr", "South Korea", "+82", "🇰🇷"));
        countyArrayList.add(new CountryModel("ss", "South Sudan", "+211", "🇸🇸"));
        countyArrayList.add(new CountryModel("es", "Spain", "+34", "🇪🇸"));
        countyArrayList.add(new CountryModel("lk", "Sri Lanka", "+94", "🇱🇰"));
        countyArrayList.add(new CountryModel("sd", "Sudan", "+249", "🇸🇩"));
        countyArrayList.add(new CountryModel("sr", "Suriname", "+597", "🇸🇷"));
        countyArrayList.add(new CountryModel("sz", "Swaziland", "+268", "🇸🇿"));
        countyArrayList.add(new CountryModel("se", "Sweden", "+46", "🇸🇪"));
        countyArrayList.add(new CountryModel("ch", "Switzerland", "+41", "🇨🇭"));
        countyArrayList.add(new CountryModel("sy", "Syrian Arab Republic", "+963", "🇸🇾"));
        countyArrayList.add(new CountryModel("tw", "Taiwan", "+886", "🇹🇼"));
        countyArrayList.add(new CountryModel("tj", "Tajikistan", "+992", "🇹🇯"));
        countyArrayList.add(new CountryModel("tz", "Tanzania", "+255", "🇹🇿"));
        countyArrayList.add(new CountryModel("th", "Thailand", "+66", "🇹🇭"));
        countyArrayList.add(new CountryModel("tl", "Timor-Leste", "+670", "🇹🇱"));
        countyArrayList.add(new CountryModel("tg", "Togo", "+228", "🇹🇬"));
        countyArrayList.add(new CountryModel("tk", "Tokelau", "+690", "🇹🇰"));
        countyArrayList.add(new CountryModel("to", "Tonga", "+676", "🇹🇴"));
        countyArrayList.add(new CountryModel("tt", "Trinidad & Tobago", "+1868", "🇹🇹"));
        countyArrayList.add(new CountryModel("tn", "Tunisia", "+216", "🇹🇳"));
        countyArrayList.add(new CountryModel("tr", "Turkey", "+90", "🇹🇷"));
        countyArrayList.add(new CountryModel("tm", "Turkmenistan", "+993", "🇹🇲"));
        countyArrayList.add(new CountryModel("tc", "Turks & Caicos Islands", "+1649", "🇹🇨"));
        countyArrayList.add(new CountryModel("tv", "Tuvalu", "+688", "🇹🇻"));
        countyArrayList.add(new CountryModel("ug", "Uganda", "+256", "🇺🇬"));
        countyArrayList.add(new CountryModel("ua", "Ukraine", "+380", "🇺🇦"));
        countyArrayList.add(new CountryModel("ae", "United Arab Emirates", "+971", "🇦🇪"));
        countyArrayList.add(new CountryModel("gb", "United Kingdom", "+44", "🇬🇧"));
        countyArrayList.add(new CountryModel("us", "United States", "+1", "🇺🇸"));
        countyArrayList.add(new CountryModel("uy", "Uruguay", "+598", "🇺🇾"));
        countyArrayList.add(new CountryModel("vi", "US Virgin Islands", "+1340", "🇻🇮"));
        countyArrayList.add(new CountryModel("uz", "Uzbekistan", "+998", "🇺🇿"));
        countyArrayList.add(new CountryModel("vu", "Vanuatu", "+678", "🇻🇺"));
        countyArrayList.add(new CountryModel("ve", "Venezuela", "+58", "🇻🇪"));
        countyArrayList.add(new CountryModel("vn", "Vietnam", "+84", "🇻🇳"));
        countyArrayList.add(new CountryModel("wf", "Wallis And Futuna", "+681", "🇼🇫"));
        countyArrayList.add(new CountryModel("ye", "Yemen", "+967", "🇾🇪"));
        countyArrayList.add(new CountryModel("zm", "Zambia", "+260", "🇿🇲"));
        countyArrayList.add(new CountryModel("zw", "Zimbabwe", "+263", "🇿🇼"));
        countyArrayList.add(new CountryModel("ax", "Åland Islands", "+358", "🇦🇽"));
    }
}
