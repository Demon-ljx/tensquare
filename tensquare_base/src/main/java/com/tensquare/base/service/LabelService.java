package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;


    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list=new ArrayList<>();
                if(label.getLabelname()!=null&&!label.getLabelname().equals("")){
                    //where labelname like "% label.getLabelname() %"
                    Predicate predicate=criteriaBuilder.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");
                    list.add(predicate);
                }
                if(label.getState()!=null&&!label.getState().equals("")){
                    //where state=label.getState()"
                    Predicate predicate=criteriaBuilder.equal(root.get("state").as(String.class),label.getState());
                    list.add(predicate);
                }
                if(label.getCount()!=null&&!label.getCount().equals("")){
                    //where count=label.getCount()
                    Predicate predicate=criteriaBuilder.equal(root.get("count").as(Long.class),label.getCount());
                    list.add(predicate);
                }
                if(label.getRecommend()!=null&&!label.getRecommend().equals("")){
                    //where recommend=label.getRecommend()
                    Predicate predicate=criteriaBuilder.equal(root.get("recommend").as(String.class),label.getRecommend());
                    list.add(predicate);
                }
                if(label.getFans()!=null&&!label.getFans().equals("")){
                    //where fans=label.getFans()
                    Predicate predicate=criteriaBuilder.equal(root.get("fans").as(Long.class),label.getFans());
                    list.add(predicate);
                }
                Predicate[] parr=new Predicate[list.size()];
                parr=list.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list=new ArrayList<>();
                if(label.getLabelname()!=null&&!label.getLabelname().equals("")){
                    //where labelname like "% label.getLabelname() %"
                    Predicate predicate=criteriaBuilder.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");
                    list.add(predicate);
                }
                if(label.getState()!=null&&!label.getState().equals("")){
                    //where state=label.getState()"
                    Predicate predicate=criteriaBuilder.equal(root.get("state").as(String.class),label.getState());
                    list.add(predicate);
                }
                if(label.getCount()!=null&&!label.getCount().equals("")){
                    //where count=label.getCount()
                    Predicate predicate=criteriaBuilder.equal(root.get("count").as(Long.class),label.getCount());
                    list.add(predicate);
                }
                if(label.getRecommend()!=null&&!label.getRecommend().equals("")){
                    //where recommend=label.getRecommend()
                    Predicate predicate=criteriaBuilder.equal(root.get("recommend").as(String.class),label.getRecommend());
                    list.add(predicate);
                }
                if(label.getFans()!=null&&!label.getFans().equals("")){
                    //where fans=label.getFans()
                    Predicate predicate=criteriaBuilder.equal(root.get("fans").as(Long.class),label.getFans());
                    list.add(predicate);
                }
                Predicate[] parr=new Predicate[list.size()];
                parr=list.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        }, pageable);
    }
}
