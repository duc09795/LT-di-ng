package com.example.charityapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charityapp.adapter.CampaignAdapter
import com.example.charityapp.databinding.ActivityMainBinding
import com.example.charityapp.models.Campaign
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var campaignAdapter: CampaignAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // 1. Cấu hình danh sách chiến dịch (Tuần 2)
        setupRecyclerView()

        // 2. Mở màn hình thông tin người dùng (Giữ code cũ của bạn)
        binding.btnProfile?.setOnClickListener {
            // Thay UserProfileActivity bằng Activity hồ sơ của bạn nếu khác tên
            val intent = Intent(this, CampaignDetailActivity::class.java)
            startActivity(intent)
        }

        // 3. Đăng xuất tài khoản (Giữ code cũ của bạn)
        binding.btnLogout?.setOnClickListener {
            auth.signOut()
            // Chuyển sang màn hình Login sau khi đăng xuất
            val intent = Intent(this, CampaignDetailActivity::class.java) // Đổi thành LoginActivity nếu có
            startActivity(intent)
            finish()
        }
    }

    private fun setupRecyclerView() {
        val sampleCampaigns = listOf(
            Campaign(
                id = "c1",
                title = "Xây trường học cho trẻ em vùng cao",
                description = "Dự án mang lại phòng học kiên cố cho học sinh vùng sâu vùng xa.",
                targetAmount = 100000000.0,
                currentAmount = 65000000.0,
                imageUrl = "https://picsum.photos/500/300"
            ),
            Campaign(
                id = "c2",
                title = "Ủng hộ đồng bào lũ lụt miền Trung",
                description = "Hỗ trợ lương thực, thuốc men và kinh phí sửa chữa nhà cửa.",
                targetAmount = 200000000.0,
                currentAmount = 150000000.0,
                imageUrl = "https://picsum.photos/501/300"
            )
        )

        campaignAdapter = CampaignAdapter(sampleCampaigns) { campaign ->
            val intent = Intent(this, CampaignDetailActivity::class.java)
            intent.putExtra("CAMPAIGN_ID", campaign.id)
            startActivity(intent)
        }

        binding.rvCampaigns.layoutManager = LinearLayoutManager(this)
        binding.rvCampaigns.adapter = campaignAdapter
    }
}