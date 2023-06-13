import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechat_padc.databinding.ViewholderAlphabetBinding
import com.example.wechat_padc.dummy.AlphabetClickListener
import com.example.wechat_padc.viewholders.AlphabetViewHolder


class AlphabetAdapter(
    private val alphabetList: List<String>,
    private val onClickAlphabet: AlphabetClickListener
) :
    RecyclerView.Adapter<AlphabetViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlphabetViewHolder {
        val itemBinding =
            ViewholderAlphabetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_alphabet, parent, false)
        return AlphabetViewHolder(itemBinding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: AlphabetViewHolder, position: Int) {
        holder.bind(alphabetList[position])
        holder.itemBinding.tvAlphabet.setOnClickListener {
            onClickAlphabet.onAlphabetClick(alphabetList[position])
        }

    }

    override fun getItemCount(): Int {
        return alphabetList.size
    }


}
