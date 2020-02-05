package com.nc.flagpicker.metric;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.search.Search;

/**
 * @author Niteen Chougula
 * @version 2.0
 * @since 2020-02-05
 */
@Service
public class CustomActuatorMetricService implements ICustomActuatorMetricService {

	@Autowired
	private MeterRegistry registry;

	private final List<ArrayList<Integer>> statusMetricsByMinute;
	private final List<String> statusList;

	public CustomActuatorMetricService() {
		super();
		statusMetricsByMinute = new ArrayList<ArrayList<Integer>>();
		statusList = new ArrayList<String>();
	}

	// API
	@Override
	public void increaseCount(final int status) {
		String counterName = "counter.status." + status;
		registry.counter(counterName).increment(1);
		if (!statusList.contains(counterName)) {
			statusList.add(counterName);
		}
	}

	// Non - API
	@Scheduled(fixedDelay = 60000) // delay for every request
	private void exportMetrics() {
		final ArrayList<Integer> statusCount = new ArrayList<Integer>();
		for (final String status : statusList) {
			Search search = registry.find(status);
			if (search != null) {
				Counter counter = search.counter();
				statusCount.add(counter != null ? ((int) counter.count()) : 0);
				registry.remove(counter);
			} else {
				statusCount.add(0);
			}
		}
		statusMetricsByMinute.add(statusCount);
	}
}