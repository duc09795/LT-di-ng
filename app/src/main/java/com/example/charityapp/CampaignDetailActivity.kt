package com.example.charityapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charityapp.adapter.DisbursementAdapter
import com.example.charityapp.databinding.ActivityCampaignDetailBinding
import com.example.charityapp.models.DisbursementReport

class CampaignDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCampaignDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCampaignDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Cấu hình danh sách nhật ký giải ngân (Tuần 4)
        setupDisbursementRecyclerView()

        // 2. Bấm nút Ủng hộ ngay để mở màn hình Quyên góp (Tuần 3)
        binding.btnDonateNow.setOnClickListener {
            val intent = Intent(this, DonateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupDisbursementRecyclerView() {
        // Dữ liệu mẫu báo cáo giải ngân
        val sampleReports = listOf(
            DisbursementReport(
                id = "1",
                title = "Đợt 1: Mua vật liệu xây dựng",
                date = "20/10/2026",
                content = "Đã mua 500 bao xi măng và 2 tấn thép để khởi công xây dựng nhà cộng đồng.",
                imageUrl = "https://picsum.photos/400/200"
            ),
            DisbursementReport(
                id = "2",
                title = "Đợt 2: Trao quà cho bà con",
                date = "25/10/2026",
                content = "Trao tặng 100 phần quà nhu yếu phẩm cho các hộ gia đình khó khăn.",
                imageUrl = "https://picsum.photos/400/201"
            )
        )

        val adapter = DisbursementAdapter(sampleReports)
        binding.rvDisbursementLogs.layoutManager = LinearLayoutManager(this)
        binding.rvDisbursementLogs.adapter = adapter
    }
}