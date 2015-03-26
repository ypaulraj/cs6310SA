package org.jacys.dscas.persist.cache;

//
//import com.mckesson.wfm.is.aos.dataaccess.UnitDao;
//import com.mckesson.wfm.is.aos.dataaccess.jdbc.UnitJdbcDao;
//import com.mckesson.wfm.is.aos.domain.Unit;
//import com.mckesson.wfm.services.FreshCache;
//import com.mckesson.wfm.services.StaleCacheException;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import java.util.*;
//import java.util.concurrent.TimeoutException;

/**
 * Stores a cache of Unit records retrieved from the AOS database for quick access. The cache
 * is refreshed automatically periodically based on the <code>refreshInterval</code> which defines
 * the numbers of minutes between cache refreshes.
 */
public class StudentCacheDao {
//	extends FreshCache<Map<Integer, Map<Integer, List<Unit>>>> implements UnitDao, Runnable {
}

//    private Log logger = LogFactory.getLog(this.getClass());
//
//    private String serviceName = "UnitCache";
//    private UnitJdbcDao unitJdbcDao;
//
//    private UnitCacheDao() {
//    }
//
//    public UnitCacheDao(UnitJdbcDao unitDao, int lifeSpan) {
//        this.unitJdbcDao = unitDao;
//        setListSpan(lifeSpan);
//        new Thread(this).start();
//    }
//
//    /**
//     * Find Unit that is in effect on the <code>date</date> provided.
//     *
//     * @param divisionId
//     * @param unitId
//     * @param date
//     * @return
//     */
//    @Override
//    public Unit find(int divisionId, int unitId, Date date) throws DataAccessException {
//        try {
//            Map<Integer, List<Unit>> unitMap = cache().get(divisionId);
//            if (unitMap != null) {
//                List<Unit> units = unitMap.get(unitId);
//                if (units != null) {
//                    for (Unit unit : units) {
//                        if (!unit.getStartDate().after(date)) return unit;
//                    }
//                }
//            }
//            return null;  // Didn't find a matching unit
//        } catch (StaleCacheException sce) {
//            throw new DataAccessException("Failed accessing cache", sce);
//        }
//    }
//
//    /**
//     * Find all Units in effect on the <code>date</code> provided.
//     *
//     * @param date in effect
//     * @return map of units by division
//     * @throws DataAccessException
//     */
//    public Map<Integer, List<Unit>> find(Date date) throws DataAccessException {
//        try {
//            Map<Integer, List<Unit>> map = new HashMap<Integer, List<Unit>>();
//            Map<Integer, Map<Integer, List<Unit>>> cache = cache();
//            for (int divisionId : cache.keySet()) {
//                List<Unit> units = new ArrayList<Unit>();
//                Map<Integer, List<Unit>> unitMap = cache.get(divisionId);
//                if (unitMap != null && unitMap.size() > 0) {
//                    for (int unitId : unitMap.keySet()) {
//                        Unit unit = find(divisionId, unitId, date);
//                        if (unit != null) {
//                            units.add(unit);
//                        }
//                    }
//                }
//                if (units.size() > 0) map.put(divisionId, units);
//            }
//            return map;
//        } catch (StaleCacheException sce) {
//            throw new DataAccessException("Failed accessing cache", sce);
//        }
//    }
//
//    /**
//     * Refresh contents of cache. When complete, the <code>lastRefresh</code> is set to
//     * current datetime and <code>nextRefresh</code> is set to current datetime plus
//     * <code>refreshInterval</code> minutes.
//     * <p/>
//     * Implementation should call <code>resetDates()</code> once cache has been
//     * replaced with new cache.
//     *
//     * @returns completion state of cache refresh where true means cache was successfully refreshed
//     */
//
//    protected boolean refreshCache() {
//        logger.debug("refreshing cache...");
//        Map<Integer, Map<Integer, List<Unit>>> cache = new HashMap<Integer, Map<Integer, List<Unit>>>();
//
//        try {
//            List<Unit> all = unitJdbcDao.findAll();
//            if (all != null) {
//                for (Unit unit : all) {
//                    Map<Integer, List<Unit>> unitMap = cache.get(unit.getDivisionId());
//                    if (unitMap == null) {
//                        unitMap = new HashMap<Integer, List<Unit>>();
//                        cache.put(unit.getDivisionId(), unitMap);
//                    }
//                    List<Unit> units = unitMap.get(unit.getUnitId());
//                    if (units == null) {
//                        units = new ArrayList<Unit>();
//                        unitMap.put(unit.getUnitId(), units);
//                    }
//                    units.add(unit);
//                }
//                setCache(cache);
//                return true;
//            } else {
//                logger.error("Failed to refresh cache; no Unit records were found!");
//                return false;
//            }
//        } catch (DataAccessException dae) {
//            logger.error("Failed to refresh cache; " + dae.getMessage());
//            return false;
//        }
//    }
//
//    @Override
//    public String getServiceName() {
//        return serviceName;
//    }
//
//    public UnitJdbcDao getUnitJdbcDao() {
//        return unitJdbcDao;
//    }
//
//    public void setUnitJdbcDao(UnitJdbcDao unitJdbcDao) {
//        this.unitJdbcDao = unitJdbcDao;
//    }
//}
