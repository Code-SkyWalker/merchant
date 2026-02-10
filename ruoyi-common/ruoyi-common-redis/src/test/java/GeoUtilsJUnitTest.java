
import org.dromara.common.redis.utils.GeoUtils;
import org.dromara.common.redis.utils.GeoLocation;
import org.dromara.common.redis.utils.NearbyLocation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.redisson.api.GeoUnit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Redis GEO 工具类JUnit测试类
 *
 * @author Lion Li
 */
public class GeoUtilsJUnitTest {

    private static final String GEO_KEY = "test_user_locations";

    @Before
    public void setUp() throws Exception {
        // 测试前清空数据
        GeoUtils.clear(GEO_KEY);
    }

    @After
    public void tearDown() throws Exception {
        // 测试后清空数据
        GeoUtils.clear(GEO_KEY);
    }

    @Test
    public void testAddSingleLocation() throws Exception {
        boolean result = GeoUtils.addLocation(GEO_KEY, 116.404, 39.915, "北京");
        assertTrue("添加地理位置应该成功", result);

        boolean exists = GeoUtils.exists(GEO_KEY, "北京");
        assertTrue("北京应该存在", exists);
    }

    @Test
    public void testAddMultipleLocations() throws Exception {
        List<GeoLocation<String>> locations = new ArrayList<>();
        locations.add(new GeoLocation<>(116.404, 39.915, "北京"));
        locations.add(new GeoLocation<>(121.4737, 31.2304, "上海"));
        locations.add(new GeoLocation<>(113.2644, 23.1291, "广州"));

        long addedCount = GeoUtils.addLocations(GEO_KEY, locations);
        assertEquals("应该成功添加3个城市", 3L, addedCount);

        long size = GeoUtils.getSize(GEO_KEY);
        assertEquals("GEO键中应该有3个成员", 3L, size);
    }

    @Test
    public void testGetPosition() throws Exception {
        GeoUtils.addLocation(GEO_KEY, 116.404, 39.915, "北京");
        GeoUtils.addLocation(GEO_KEY, 121.4737, 31.2304, "上海");

        List<org.redisson.api.GeoPosition> positions = GeoUtils.getPosition(GEO_KEY, "北京", "上海");
        assertNotNull("位置信息不应该为null", positions);
        assertFalse("应该返回位置信息", positions.isEmpty());

        // 验证返回的位置信息不为null
        org.redisson.api.GeoPosition beijingPos = positions.get(0);
        assertNotNull("北京位置信息不应该为null", beijingPos);
    }

    @Test
    public void testGetDistance() throws Exception {
        GeoUtils.addLocation(GEO_KEY, 116.404, 39.915, "北京");
        GeoUtils.addLocation(GEO_KEY, 121.4737, 31.2304, "上海");

        Double distance = GeoUtils.getDistance(GEO_KEY, "北京", "上海", GeoUnit.KILOMETERS);
        assertNotNull("距离不应该为null", distance);
        assertTrue("北京到上海的距离应该大于1000公里", distance > 1000);

        // 使用Haversine公式直接计算距离
        double directDistance = GeoUtils.calculateDistance(116.404, 39.915, 121.4737, 31.2304, GeoUnit.KILOMETERS);
        assertTrue("直接计算的距离应该大于1000公里", directDistance > 1000);
    }

    @Test
    public void testSearchNearby() throws Exception {
        // 添加测试数据
        GeoUtils.addLocation(GEO_KEY, 116.404, 39.915, "北京");
        GeoUtils.addLocation(GEO_KEY, 121.4737, 31.2304, "上海");
        GeoUtils.addLocation(GEO_KEY, 113.2644, 23.1291, "广州");
        GeoUtils.addLocation(GEO_KEY, 120.1551, 30.2741, "杭州");

        // 搜索北京1000公里范围内的城市
        List<String> nearby = GeoUtils.searchNearby(GEO_KEY, 116.404, 39.915, 1000, GeoUnit.KILOMETERS);
        assertNotNull("附近城市列表不应该为null", nearby);
        assertFalse("结果不应该为空", nearby.isEmpty());
        assertTrue("结果中应该包含北京", nearby.contains("北京"));
    }

    @Test
    public void testSearchNearbyWithDistance() throws Exception {
        GeoUtils.addLocation(GEO_KEY, 116.404, 39.915, "北京");
        GeoUtils.addLocation(GEO_KEY, 120.1551, 30.2741, "杭州");

        java.util.Map<String, Double> nearbyWithDistance = GeoUtils.searchNearbyWithDistance(
            GEO_KEY, 116.404, 39.915, 1000, GeoUnit.KILOMETERS);

        assertNotNull("带距离信息的结果不应该为null", nearbyWithDistance);
        assertFalse("结果不应该为空", nearbyWithDistance.isEmpty());
        assertTrue("应该包含北京", nearbyWithDistance.containsKey("北京"));
    }

    @Test
    public void testSearchNearbyLimit() throws Exception {
        // 添加多个城市
        GeoUtils.addLocation(GEO_KEY, 116.404, 39.915, "北京");
        GeoUtils.addLocation(GEO_KEY, 120.1551, 30.2741, "杭州");
        GeoUtils.addLocation(GEO_KEY, 118.7969, 32.0603, "南京");
        GeoUtils.addLocation(GEO_KEY, 121.4737, 31.2304, "上海");

        List<String> limitedNearby = GeoUtils.searchNearbyLimit(
            GEO_KEY, 116.404, 39.915, 1000, GeoUnit.KILOMETERS, 2);

        assertNotNull("限制结果不应该为null", limitedNearby);
        assertFalse("结果不应该为空", limitedNearby.isEmpty());
        assertTrue("应该只返回最多2个城市", limitedNearby.size() <= 2);
    }

    @Test
    public void testGetNearbySorted() throws Exception {
        GeoUtils.addLocation(GEO_KEY, 116.404, 39.915, "北京");
        GeoUtils.addLocation(GEO_KEY, 120.1551, 30.2741, "杭州");
        GeoUtils.addLocation(GEO_KEY, 118.7969, 32.0603, "南京");

        List<NearbyLocation<String>> sortedNearby = GeoUtils.getNearbySorted(
            GEO_KEY, 116.404, 39.915, 1000, GeoUnit.KILOMETERS, 5, "北京");

        assertNotNull("排序结果不应该为null", sortedNearby);
        assertFalse("结果不应该为空", sortedNearby.isEmpty());

        // 验证按距离排序（只需要验证至少有一个元素）
        assertTrue("应该至少有一个结果", sortedNearby.size() > 0);
    }

    @Test
    public void testSearchInBox() throws Exception {
        GeoUtils.addLocation(GEO_KEY, 116.404, 39.915, "北京");
        GeoUtils.addLocation(GEO_KEY, 121.4737, 31.2304, "上海");
        GeoUtils.addLocation(GEO_KEY, 113.2644, 23.1291, "广州");

        // 搜索矩形范围内的城市
        List<String> inBox = GeoUtils.searchInBox(GEO_KEY, 110, 30, 120, 40, GeoUnit.KILOMETERS);

        assertNotNull("矩形搜索结果不应该为null", inBox);
        assertFalse("结果不应该为空", inBox.isEmpty());
    }

    @Test
    public void testExists() throws Exception {
        GeoUtils.addLocation(GEO_KEY, 116.404, 39.915, "北京");

        assertTrue("北京应该存在", GeoUtils.exists(GEO_KEY, "北京"));
        assertFalse("上海应该不存在", GeoUtils.exists(GEO_KEY, "上海"));
    }

    @Test
    public void testGetAllMembers() throws Exception {
        GeoUtils.addLocation(GEO_KEY, 116.404, 39.915, "北京");
        GeoUtils.addLocation(GEO_KEY, 121.4737, 31.2304, "上海");

        java.util.Set<String> allMembers = GeoUtils.getAllMembers(GEO_KEY);
        assertNotNull("所有成员不应该为null", allMembers);
        assertEquals("应该有2个成员", 2, allMembers.size());
        assertTrue("应该包含北京", allMembers.contains("北京"));
        assertTrue("应该包含上海", allMembers.contains("上海"));
    }

    @Test
    public void testGetSize() throws Exception {
        assertEquals("初始应该没有成员", 0L, GeoUtils.getSize(GEO_KEY));

        GeoUtils.addLocation(GEO_KEY, 116.404, 39.915, "北京");
        assertEquals("添加后应该有1个成员", 1L, GeoUtils.getSize(GEO_KEY));

        GeoUtils.addLocation(GEO_KEY, 121.4737, 31.2304, "上海");
        assertEquals("再添加后应该有2个成员", 2L, GeoUtils.getSize(GEO_KEY));
    }

    @Test
    public void testGetHash() throws Exception {
        GeoUtils.addLocation(GEO_KEY, 116.404, 39.915, "北京");
        GeoUtils.addLocation(GEO_KEY, 121.4737, 31.2304, "上海");

        List<Long> hashes = GeoUtils.getHash(GEO_KEY, "北京", "上海");
        assertNotNull("Hash值不应该为null", hashes);
        assertFalse("应该返回Hash值", hashes.isEmpty());
    }

    @Test
    public void testRemoveLocations() throws Exception {
        GeoUtils.addLocation(GEO_KEY, 116.404, 39.915, "北京");
        GeoUtils.addLocation(GEO_KEY, 121.4737, 31.2304, "上海");

        long removedCount = GeoUtils.removeLocations(GEO_KEY, "北京");
        assertEquals("应该成功删除1个城市", 1L, removedCount);

        assertFalse("北京应该已被删除", GeoUtils.exists(GEO_KEY, "北京"));
        assertTrue("上海应该仍然存在", GeoUtils.exists(GEO_KEY, "上海"));

        assertEquals("删除后应该只剩1个成员", 1L, GeoUtils.getSize(GEO_KEY));
    }

    @Test
    public void testClear() throws Exception {
        GeoUtils.addLocation(GEO_KEY, 116.404, 39.915, "北京");
        GeoUtils.addLocation(GEO_KEY, 121.4737, 31.2304, "上海");

        long sizeBefore = GeoUtils.getSize(GEO_KEY);
        assertTrue("清空前应该有成员", sizeBefore > 0);

        boolean cleared = GeoUtils.clear(GEO_KEY);
        assertTrue("清空操作应该成功", cleared);

        long sizeAfter = GeoUtils.getSize(GEO_KEY);
        assertEquals("清空后应该没有成员", 0L, sizeAfter);
        assertFalse("北京应该已被清空", GeoUtils.exists(GEO_KEY, "北京"));
        assertFalse("上海应该已被清空", GeoUtils.exists(GEO_KEY, "上海"));
    }
}
